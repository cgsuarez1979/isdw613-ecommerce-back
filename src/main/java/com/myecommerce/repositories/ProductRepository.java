package com.myecommerce.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.myecommerce.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    
    List<Product> findByName(String name);

    List<Product> findByNameLike(String name, Pageable pageable);

    List<Product> findByPriceGreaterThan(Float price); // price > 120

    List<Product> findByPriceGreaterThanEqual(Float price); // price >= 120

    List<Product> findByPriceLessThan(Float price); // price < 120

    List<Product> findByPriceLessThanEqual(Float price); // price <= 120

    List<Product> findByPriceBetween(Float startPrice, Float endPrice);

    List<Product> findByNameAndDescription(String name, String description); //buscar por nombre y description

    List<Product> findByNameOrDescription(String name, String description); //buscar por nombre O description

    List<Product> findByNameOrderByName(String name);

    List<Product> findByNameOrderByNameDesc(String name);

    @Query("SELECT p FROM Product p WHERE p.category.name = :name")
    List<Product> findByCategoryName(@Param("name") String categoryName);
}
