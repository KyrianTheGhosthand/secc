package com.example.demosale.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private String unitPrice;
    private int stockedAmount;
    @Column(length = 1000)
    private String desc;
    private String manufacturer;
    private String type;
    private String category;
    @Enumerated(EnumType.STRING)
    private Condition condition;
    private String imageLink;

}
