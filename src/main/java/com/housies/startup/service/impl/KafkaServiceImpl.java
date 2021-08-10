package com.housies.startup.service.impl;

import com.housies.startup.GeneralEnumeration;
import com.housies.startup.model.Customer;
import com.housies.startup.service.KafkaService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Properties;

@Service
public class KafkaServiceImpl implements KafkaService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;



    @Override
    public void send(String message, String topic) {
        kafkaTemplate.send(GeneralEnumeration.TOPIC, message);
    }

    @Override
    public void send(Customer message, String topic){
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "com.housies.startup.configuration.BaseSerializer");

        Producer<String, Customer> producer = new KafkaProducer<>(props);

        try {
            producer.send(new ProducerRecord<>(GeneralEnumeration.TOPIC, "SUP", message)).get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("SupplierProducer Completed.");
        producer.close();
    }



    @Override
    @KafkaListener(topics = "PLAINTEXT", groupId = "1")
    public void consume(Object data){
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092,localhost:9093");
        props.put("group.id", "group_id");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "com.housies.startup.configuration.BaseDeserializer");

        KafkaConsumer<String, Customer> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(GeneralEnumeration.TOPIC));

        while (true) {
            ConsumerRecords<String, Customer> records = consumer.poll(100);
                for (ConsumerRecord<String, Customer> record : records) {
                    System.out.println("message: " + record.toString());
                }
        }

    }
}
