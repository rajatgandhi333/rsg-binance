package com.rsg.crypto.service;

import com.rsg.crypto.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BinanceService {

    @Autowired
    private RestTemplate restTemplate;

    public String getCryptoPrice() {
        return restTemplate.getForObject(Constant.BINANCE_API_URL, String.class);
    }

}
