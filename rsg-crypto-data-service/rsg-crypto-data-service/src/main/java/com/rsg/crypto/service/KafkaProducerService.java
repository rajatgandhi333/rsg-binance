package com.rsg.crypto.service;

import com.rsg.crypto.config.KafkaConfig;
import com.rsg.crypto.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendAlert(String message) {
        kafkaTemplate.send(Constant.SEND_PRICE_ALERT_TOPIC, message);
    }

}
