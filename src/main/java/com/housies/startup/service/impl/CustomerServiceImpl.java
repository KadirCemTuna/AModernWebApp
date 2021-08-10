package com.housies.startup.service.impl;

import com.housies.startup.GeneralEnumeration;
import com.housies.startup.model.Address;
import com.housies.startup.model.Customer;
import com.housies.startup.model.Order;
import com.housies.startup.model.Price;
import com.housies.startup.repository.CustomerRepository;
import com.housies.startup.service.CustomerService;
import com.housies.startup.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private KafkaService kafkaService;

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


        Customer customer = new Customer(
                "kadir",
                request.getEmail(),
                GeneralEnumeration.Gender.MALE,
                address,
                new ArrayList<Order>(),
                price,
                LocalDateTime.now()
        );

        Customer savedCustomer = customerRepository.insert(customer);

        kafkaService.send(savedCustomer, GeneralEnumeration.TOPIC);


        return savedCustomer;
    }
}
