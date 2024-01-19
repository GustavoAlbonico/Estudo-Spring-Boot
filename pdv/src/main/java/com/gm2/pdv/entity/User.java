package com.gm2.pdv.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String nome;

    @Column(length = 30, nullable = false,unique = true)
    private String username;

    @Column(length = 60, nullable = false)
    private String password;

    private boolean isEnable;

    @OneToMany(mappedBy = "user")
    private List<Sale> sales;


}
