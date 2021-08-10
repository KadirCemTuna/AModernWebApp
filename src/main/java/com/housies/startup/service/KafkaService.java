package com.housies.startup.service;

import com.housies.startup.model.Customer;
import org.springframework.kafka.annotation.KafkaListener;

public interface KafkaService {
    void send(String message, String topic);

    void send(Customer message, String topic);



    @KafkaListener(topics = "PLAINTEXT", groupId = "1")
    void consume(Object data);
}
