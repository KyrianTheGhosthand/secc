package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String address;
    private String estimatedTime;
    @Column(columnDefinition = "int default '1'")
    private int status;
    private int total;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "shipper_id")
    private Shipper shipper;
    @ManyToMany
    @JoinTable(name = "product_receipt", joinColumns = @JoinColumn(name = "receipt_id")
    , inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;



}