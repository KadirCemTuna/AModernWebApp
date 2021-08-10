package com.housies.startup.model;

import com.housies.startup.GeneralEnumeration;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Price {
    private GeneralEnumeration.Currency currency;
    private Double amount;
}
