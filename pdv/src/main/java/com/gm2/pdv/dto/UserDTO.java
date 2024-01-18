package com.gm2.pdv.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    @NotBlank(message = "Campo nome é obrigatório!")
    private String nome;

    @NotBlank(message = "O campo username é obrigatório!")
    private String username;

    @NotBlank(message = "O campo senha é obrigatório!")
    private String password;

    private boolean isEnable;

}
