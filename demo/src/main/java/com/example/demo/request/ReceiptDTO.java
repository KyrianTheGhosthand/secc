package com.example.demo.request;

import com.example.demo.model.Customer;
import com.example.demo.model.Product;
import com.example.demo.model.Shipper;

import java.util.List;

public class ReceiptDTO {
    private int id;
    private String address;
    private String estimatedTime;
    private int status;
    private int total;
    private Customer customer;
    private Shipper shipper;
    private List<Product> products;
}
