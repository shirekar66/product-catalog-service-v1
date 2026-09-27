package com.scode.microservices.product_service.service.impl;

import com.scode.microservices.product_service.dto.ProductDto;
import com.scode.microservices.product_service.entity.Product;
import com.scode.microservices.product_service.exception.ProductNotFoundException;
import com.scode.microservices.product_service.mapper.ProductMapper;
import com.scode.microservices.product_service.repositories.ProductRepo;
import com.scode.microservices.product_service.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {

        Product product = ProductMapper.toEntity(productDto);
        Product savedProduct = productRepo.save(product);

        log.info("Product created successfully: id={}, name={}",
                savedProduct.getId(),
                savedProduct.getName());

        return ProductMapper.toDto(savedProduct);
    }

    @Override
    public ProductDto getProductById(Long id) {

        Product product = getProductEntity(id);

        log.info("Product fetched: id={}", id);

        return ProductMapper.toDto(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> getAllProducts() {

        List<Product> products = productRepo.findAll();

        log.info("Fetched {} products", products.size());

        return products.stream()
                .map(ProductMapper::toDto)
                .toList();
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {

        Product existingProduct = getProductEntity(id);

        existingProduct.setName(productDto.getName());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setInStock(productDto.getInStock());

        log.info("Product updated successfully: id={}", id);

        return ProductMapper.toDto(existingProduct);
    }

    @Override
    public void deleteProduct(Long id) {

        Product product = getProductEntity(id);
        productRepo.delete(product);

        log.info("Product deleted successfully: id={}", id);
    }

    private Product getProductEntity(Long id) {
        return productRepo.findById(id)
                .orElseThrow(
                        () -> new ProductNotFoundException(
                                "Product Not found with id: " + id));
    }
}