package com.academy.dream_shop.services.product;

import com.academy.dream_shop.exceptions.ProductNotFoundException;
import com.academy.dream_shop.exceptions.ResourceNotFound;
import com.academy.dream_shop.models.Category;
import com.academy.dream_shop.models.Product;
import com.academy.dream_shop.repository.CategoryRepository;
import com.academy.dream_shop.repository.ProductRepository;
import com.academy.dream_shop.request.ProductRequest;
import com.academy.dream_shop.request.ProductUpdateRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product addProduct(ProductRequest req) {
        Category category = Optional.ofNullable(categoryRepository.findByName(req.getCategory().getName()))
                .orElseGet(() -> {
                    Category newCategory = new Category(req.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });
        req.setCategory(category);
        return productRepository.save(createProducts(req, category));

    }

    private Product createProducts(ProductRequest req, Category category){
        return new Product(
                req.getName(),
                req.getBrand(),
                req.getPrice(),
                req.getInventory(),
                req.getDescription(),
                category
        );
    }


    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id " + id));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id)
                .ifPresentOrElse(productRepository::delete, () -> {
                    throw new ProductNotFoundException("Product not found");
                });
    }

    @Override
    public Product updateProduct(ProductUpdateRequest req, Long id) {
        return productRepository.findById(id)
                .map(existingProduct -> updateExistingProduct(existingProduct, req))
                .map(productRepository::save)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

    }

    private Product updateExistingProduct(Product product, ProductUpdateRequest req){
        product.setName(req.getName());
        product.setBrand(req.getBrand());
        product.setPrice(req.getPrice());
        product.setInventory(req.getInventory());
        product.setDescription(req.getDescription());
        Category category = categoryRepository.findByName(req.getCategory().getName());
        product.setCategory(category);
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductByCategoryName(String categoryName) {
        return productRepository.findProductByCategoryName(categoryName);
    }

    @Override
    public List<Product> getProductByBrand(String brand) {
        return productRepository.findProductByBrand(brand);
    }

    @Override
    public List<Product> getProductByCategoryNameAndBrand(String categoryName, String brand) {
        return productRepository.findProductByCategoryNameAndBrand(categoryName, brand);
    }


    @Override
    public List<Product> getProductByName(String name) {
        return productRepository.findProductByName(name);
    }

    @Override
    public List<Product> getProductByBrandAndName(String name, String brand) {
        return productRepository.findProductByBrandAndName(brand, name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }
}
