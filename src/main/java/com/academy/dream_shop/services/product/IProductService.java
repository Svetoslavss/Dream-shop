package com.academy.dream_shop.services.product;

import com.academy.dream_shop.models.Product;
import com.academy.dream_shop.request.ProductRequest;
import com.academy.dream_shop.request.ProductUpdateRequest;

import java.util.List;

public interface IProductService {

    Product addProduct(ProductRequest req);
    Product getProductById(Long id);
    void deleteProductById(Long id);
    Product updateProduct(ProductUpdateRequest req, Long id);
    List<Product> getAllProducts();
    List<Product> getProductByCategoryName(String categoryName);
    List<Product> getProductByBrand(String brand);
    List<Product> getProductByCategoryNameAndBrand(String categoryName, String brand);
    List<Product> getProductByName(String name);
    List<Product> getProductByBrandAndName(String name, String brand);
    Long countProductsByBrandAndName(String brand, String name);
}

