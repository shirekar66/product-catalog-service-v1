package com.scode.microservices.product_service.repositories;

import com.scode.microservices.product_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Long> {
}
