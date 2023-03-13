package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "vote")

public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String message;
    private int rate;

    @OneToOne
    @JoinColumn(name = "receipt_id")
    private Receipt receipt;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;


}
