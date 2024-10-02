package com.rsg.crypto.scheduler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rsg.crypto.model.CryptoPrice;
import com.rsg.crypto.repository.CryptoRepository;
import com.rsg.crypto.service.BinanceService;
import com.rsg.crypto.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CryptoScheduler {

    @Autowired
    private BinanceService binanceService;

    @Autowired
    private CryptoRepository cryptoRepository;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Scheduled(fixedRate = 60000)
    public void fetchAndSavePrice() throws Exception {
        String response = binanceService.getCryptoPrice();
        JsonNode jsonNode = objectMapper.readTree(response);

        double price=jsonNode.get("price").asDouble();
        if(price> 30000){
            kafkaProducerService.sendAlert("Prices have crosses $30000: "+price);
        }

        CryptoPrice cryptoPrice = new CryptoPrice();
        cryptoPrice.setSymbol(jsonNode.get("symbol").asText());
        cryptoPrice.setPrice(jsonNode.get("price").asDouble());
        cryptoPrice.setTimeStamp(LocalDateTime.now());
        cryptoRepository.save(cryptoPrice);
    }
}
