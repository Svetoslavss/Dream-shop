package com.academy.dream_shop.services.product.category;

import com.academy.dream_shop.models.Category;
import com.academy.dream_shop.models.Product;
import com.academy.dream_shop.request.CategoryUpdateRequest;
import com.academy.dream_shop.request.ProductRequest;

import java.util.List;

public interface ICategoryService {

    Category addCategory(Category category);
    Category getCategoryById(Long id);
    Category getCategoryByName(String categoryName);
    Category updateCategory(CategoryUpdateRequest req , Long categoryId);
    void deleteCategoryId(Long id);
    List<Category> getAllCategories();

}
