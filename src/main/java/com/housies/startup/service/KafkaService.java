package com.housies.startup.service;

import org.springframework.kafka.annotation.KafkaListener;

public interface KafkaService {
    void send(String message, String topic);

    @KafkaListener(topics = "PLAINTEXT", groupId = "1")
    void consume(String message);
}
