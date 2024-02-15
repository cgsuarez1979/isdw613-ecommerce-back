package com.myecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myecommerce.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    
}
