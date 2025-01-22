package com.academy.dream_shop.services.product.category;

import com.academy.dream_shop.models.Category;
import com.academy.dream_shop.models.Product;
import com.academy.dream_shop.request.CategoryUpdateRequest;
import com.academy.dream_shop.request.ProductRequest;

public interface ICategoryService {

    Category getCategoryById(Long id);
    Category addProductInCategory(Category category, ProductRequest req);
    void deleteCategoryId(Long id);
    Category updateCategory(CategoryUpdateRequest req , Long categoryId);

}
