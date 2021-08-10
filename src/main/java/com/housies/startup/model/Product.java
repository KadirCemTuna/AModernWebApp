package com.housies.startup.model;

import lombok.Data;

@Data
public class Product extends BaseModel{
    private String name;
    private Double price;
}
