package com.example.springboot.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "TB_PRODUCTS")
@Data
public class ProductModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idProduct;
    private String name;
    private BigDecimal price;
}
