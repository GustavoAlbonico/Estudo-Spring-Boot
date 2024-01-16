package com.gm2.pdv.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
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
    @NotBlank(message = "Campo nome é obrigatório!")
    private String nome;

    @Column(length = 30, nullable = false)
    @NotBlank(message = "O campo username é obrigatório!")
    private String username;

    @Column(length = 40, nullable = false)
    @NotBlank(message = "O campo senha é obrigatório!")
    private String password;

    private boolean isEnable;

    @OneToMany(mappedBy = "user")
    private List<Sale> sales;


}
