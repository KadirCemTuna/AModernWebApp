package com.housies.startup.model;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private Product product;
    private Date orderDate;
    private Price price;
}
