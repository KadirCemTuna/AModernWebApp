package com.housies.startup.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address extends BaseModel{
    private String country;
    private String city;
    private String postCode;
}
