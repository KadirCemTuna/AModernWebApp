package com.housies.startup.service.impl;

import com.housies.startup.GeneralEnumeration;
import com.housies.startup.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaServiceImpl implements KafkaService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void send(String message, String topic) {
        kafkaTemplate.send(GeneralEnumeration.TOPIC, message);
    }

    @Override
    @KafkaListener(topics = "PLAINTEXT", groupId = "1")
    public void consume(String message){
        System.out.println("message: "+ message);
    }
}
