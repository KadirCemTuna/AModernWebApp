package com.housies.startup.controller;

import com.housies.startup.model.Customer;
import com.housies.startup.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }


    @GetMapping("/{email}")
    public Customer createCustomer(@PathVariable(name = "email") String email){
        return customerService.createCustomer(new Customer(email));
    }

    @PutMapping()
    public Customer updateCustomer(@RequestBody Customer request){
        return customerService.createCustomer(request);
    }
}
