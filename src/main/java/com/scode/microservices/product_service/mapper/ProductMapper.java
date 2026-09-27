package com.scode.microservices.product_service.mapper;


import com.scode.microservices.product_service.dto.ProductDto;
import com.scode.microservices.product_service.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public static ProductDto toDto(Product product) {

        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getInStock()
        );
    }

    public static Product toEntity(ProductDto dto) {


        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setInStock(dto.getInStock());

        return product;
    }
}