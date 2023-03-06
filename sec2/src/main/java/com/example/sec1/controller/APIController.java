package com.example.sec1.controller;

import com.example.sec1.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController

@RequestMapping("/api")
public class APIController {
    @GetMapping("/products")
    public List<Product> getProducts(){
        List<Product> result = new ArrayList<>();
        result.add(new Product("Coffee Grinder", 175));
        result.add(new Product("Coffee Smoker", 180));
        result.add(new Product("Coffee Shredder", 200));
        return result;
    }

    @GetMapping("/knives")
    public String getKnives(){
        return "Insert Random Knife here";
    }

    @GetMapping("/secret")
    public String getSecret(){
        return "get rekt lmao";
    }


}
