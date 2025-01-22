package com.academy.dream_shop.repository;

import com.academy.dream_shop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
   // List<Product> findProductByCategory(String category);
  //  List<Product> findProductByCategoryAndBrand(String category, String brand);
    List<Product> findProductByBrand(String brand);
    List<Product> findProductByName(String name);
    List<Product> findProductByBrandAndName(String name, String brand);
    Long countByBrandAndName(String brand, String name);
}

