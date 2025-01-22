package com.academy.dream_shop.request;

import com.academy.dream_shop.models.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryUpdateRequest {
    private Long id;
    private String name;
    private List<Product> product;
}
