package com.example.demosale.dto;

import com.example.demosale.entity.Condition;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
public class CreateProductDTO {
    @NotEmpty(message = "Name is required")
    private String name;

    @NotNull(message = "Price is required")
    private BigDecimal price;

    @NotEmpty(message = "Unit price is required")
    private String unitPrice;

    @NotNull(message = "Units in stock is required")
    private Long unitsInStock;

    private String description;
    private String manufacturer;
    private String category;
    private Condition condition;
    private String imageFile;
}
