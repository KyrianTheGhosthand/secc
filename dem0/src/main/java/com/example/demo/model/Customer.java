package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(columnDefinition = "varchar(200) default 'Open'")
    private String name;
    private String phone;

    @OneToMany(mappedBy = "customer")
    private List<Vote> votes;

    @OneToMany(mappedBy = "customer")
    private List<Receipt> receipts;

}
