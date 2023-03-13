package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "account_num")
    private String accountNum;
    private int balance;

    @OneToOne
    @JoinColumn(name= "customer_id")
    private Customer customer;
    @OneToOne
    @JoinColumn(name = "shipper_id")
    private Shipper shipper;
}
