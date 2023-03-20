package com.example.demosale.controller;

import com.example.demosale.dto.CreateProductDTO;
import com.example.demosale.dto.ProductInfoDTO;
import com.example.demosale.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public List<ProductInfoDTO> listProducts(){return productService.listProducts();}

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ProductInfoDTO getProduct(@PathVariable Long id){
        return productService.getProduct(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public void createProduct(@RequestBody @Valid CreateProductDTO dto){
        productService.createProduct(dto);
    }
}
