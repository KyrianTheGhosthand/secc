package com.example.demosale.service;

import com.example.demosale.dto.CreateProductDTO;
import com.example.demosale.dto.ProductInfoDTO;

import java.util.List;

public interface ProductService {
    List<ProductInfoDTO> listProducts();

    ProductInfoDTO getProduct(Long id);

    void createProduct(CreateProductDTO dto);
}
