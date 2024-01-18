package com.gm2.pdv.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSaleDTO {

    @NotNull(message = "O campo item é obrigatório!")
    private long productid;

    @NotNull(message = "O campo quantidade é obbrigatório!")
    @Min(value = 1, message = "A quantidade minima permitida é 1")
    private int quantity;
}
