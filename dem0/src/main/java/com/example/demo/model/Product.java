package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int quantity;
    private double price;
    private float rate;
    private double weight;
    private String description;

    @ManyToMany(mappedBy = "products")
    private List<Receipt> receipts;
    @OneToOne
    @JoinColumn(name = "vote_id")
    private Vote vote;
}
