package com.example.demosale.dto;

import com.example.demosale.entity.Condition;
import com.example.demosale.entity.Product;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductInfoDTO {
    private final Long id;
    private final String name;
    private final BigDecimal price;
    private final String unitPrice;
    private final int unitsInStock;
    private final String description;
    private final String manufacturer;
    private final String category;
    private final Condition condition;
    private final String imageLink;

    public ProductInfoDTO(Product product) {
        id = product.getId();
        name = product.getName();
        price = product.getPrice();
        unitPrice = product.getUnitPrice();
        unitsInStock = product.getStockedAmount();
        description = product.getDesc();
        manufacturer = product.getManufacturer();
        category = product.getCategory();
        condition = product.getCondition();
        imageLink = product.getImageLink();
    }
}
