package com.rsg.crypto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class CryptoConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
