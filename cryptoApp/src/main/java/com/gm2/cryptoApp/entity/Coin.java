package com.gm2.cryptoApp.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;


@Entity
@Table(name = "coin")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Coin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PRICE", precision = 10, scale = 2, nullable = false)
    private BigDecimal price;

    @Column(name = "QUANTITY", precision = 20, scale = 5, nullable = false)
    private BigDecimal quantity;

    @Column(name = "DATETIME")
    private Timestamp dateTime;

}
