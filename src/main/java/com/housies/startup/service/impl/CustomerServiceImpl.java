package com.housies.startup.service.impl;

import com.housies.startup.GeneralEnumeration;
import com.housies.startup.model.Address;
import com.housies.startup.model.Customer;
import com.housies.startup.model.Order;
import com.housies.startup.model.Price;
import com.housies.startup.repository.CustomerRepository;
import com.housies.startup.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "PLAINTEXT";

    @Override
    public Customer createCustomer(Customer request) {
        Address address = new Address(
                "Turkey",
                "İstanbul",
                "123456"
        );
        Price price = new Price(
                GeneralEnumeration.Currency.DOLLAR,
                100.0
        );

        String email = "atestkadir@gmail.com";

        Customer customer = new Customer(
                "kadir",
                email,
                GeneralEnumeration.Gender.MALE,
                address,
                new ArrayList<Order>(),
                price,
                LocalDateTime.now()
        );

        Customer savedCustomer = customerRepository.insert(customer);

        String message = savedCustomer.getId();
        kafkaTemplate.send(TOPIC, message);

        return savedCustomer;
    }

    @KafkaListener(topics = "PLAINTEXT", groupId = "1")
    public void consume(String message){
        System.out.println(message);
    }
}
