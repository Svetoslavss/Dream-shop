package com.academy.dream_shop.request;

import com.academy.dream_shop.models.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateRequest {
        private Long id;
        private String name;
        private String brand;
        private BigDecimal price;
        private int inventory;
        private String description;
        private Category category;

    }

