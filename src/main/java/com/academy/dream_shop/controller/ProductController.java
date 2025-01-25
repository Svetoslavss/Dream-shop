package com.academy.dream_shop.controller;

import com.academy.dream_shop.exceptions.ProductNotFoundException;
import com.academy.dream_shop.models.Product;
import com.academy.dream_shop.request.ProductRequest;
import com.academy.dream_shop.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;


    @PostMapping("/add-product")
    public ResponseEntity<Product> addProduct(@RequestBody ProductRequest req){
        try {
            Product product = productService.addProduct(req);
            return ResponseEntity.ok(product);
        } catch (ProductNotFoundException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/view-all-products")
    public ResponseEntity<List<Product>> viewAllProducts(){
        try {
            List<Product> products = productService.getAllProducts();
            if (products.isEmpty()){
                throw new ProductNotFoundException("Products not found");
            }
            return ResponseEntity.ok(products);
        } catch (ProductNotFoundException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long id){
        try {
            productService.deleteProductById(id);
            return ResponseEntity.ok("Product deleted successfully.");
        } catch (ProductNotFoundException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
