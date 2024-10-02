package com.rsg.crypto.notification.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @KafkaListener
    public void listenForPriceeAlerts(String alertMessage){
        System.out.println("Received Alert: "+alertMessage);
        sendMessage(alertMessage);
    }

    private void sendMessage(String message){
        System.out.println("Notification sent: "+message);
    }
}
