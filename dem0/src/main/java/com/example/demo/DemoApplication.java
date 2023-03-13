package com.example.demo;

import com.example.demo.model.Wallet;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ReceiptRepository receiptRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ShipperRepository shipperRepository;
    @Autowired
    WalletRepository walletRepository;
    @Autowired
    VoteRepository voteRepository;

    @Override
    public void run(String... args) throws Exception{

    }


}
