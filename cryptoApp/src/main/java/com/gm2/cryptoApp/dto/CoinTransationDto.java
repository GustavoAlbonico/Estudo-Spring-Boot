package com.gm2.cryptoApp.dto;

import java.math.BigDecimal;

public class CoinTransationDto {

    private String name;
    private BigDecimal quantity;

    public CoinTransationDto(String name, BigDecimal quantity){
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }
}
