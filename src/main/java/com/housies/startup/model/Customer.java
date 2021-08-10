package com.housies.startup.model;

import com.housies.startup.GeneralEnumeration.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document
@NoArgsConstructor
public class Customer {
    @Id
    private String id;
    private String name;
    @Indexed(unique = true)
    private String email;
    private Gender gender;
    private Address address;
    private List<Order> orders;
    private Price totalSpent;
    private LocalDateTime cdate;

    public Customer(String name, String email, Gender gender, Address address, List<Order> orders, Price totalSpent, LocalDateTime cdate) {
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.orders = orders;
        this.totalSpent = totalSpent;
        this.cdate = cdate;
    }

    public Customer(String email) {
        this.email = email;
    }
}
