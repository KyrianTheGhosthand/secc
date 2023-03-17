package com.example.demosale.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    private String unitPrice;
    private int stockedAmount;
    private String desc;
    private String manufacturer;
    private String type;
    private String category;

    private Condition condition;
    private String imageLink;

}
