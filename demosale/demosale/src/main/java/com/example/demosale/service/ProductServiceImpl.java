package com.example.demosale.service;

import com.example.demosale.dto.CreateProductDTO;
import com.example.demosale.dto.ProductInfoDTO;
import com.example.demosale.entity.Product;
import com.example.demosale.exception.ApiException;
import com.example.demosale.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<ProductInfoDTO> listProducts() {
        return productRepository.findAll().stream()
                .map(ProductInfoDTO::new)
                .toList();
    }

    @Override
    public ProductInfoDTO getProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ApiException(404, "Product not found"));
        return new ProductInfoDTO(product);
    }

    @Override
    @Transactional
    public void createProduct(CreateProductDTO dto){
        Product product = new Product(
//                null,
//                dto.getName(),
//                dto.getPrice(),
//                dto.getUnitPrice(),
//                dto.getUnitsInStock(),
//                dto.getDescription(),
//                dto.getManufacturer(),
//                dto.getCategory(),
//                dto.getCondition(),
//                dto.getImageFile()
        );
        productRepository.save(product);
    }
}
