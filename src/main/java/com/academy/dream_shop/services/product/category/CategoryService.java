package com.academy.dream_shop.services.product.category;

import com.academy.dream_shop.exceptions.CategoryNotFound;
import com.academy.dream_shop.models.Category;
import com.academy.dream_shop.repository.CategoryRepository;
import com.academy.dream_shop.request.CategoryUpdateRequest;
import com.academy.dream_shop.request.ProductRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService{

    private CategoryRepository categoryRepository;

    @Override
    public Category addCategory(Category category) {
        return null;
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFound("Category not found"));
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        return null;
    }

    @Override
    public Category addProductInCategory(Category category, ProductRequest req) {
        return null;
    }

    @Override
    public Category updateCategory(CategoryUpdateRequest req, Long categoryId) {
        return null;
    }

    @Override
    public void deleteCategoryId(Long id) {

    }

    @Override
    public List<Category> getAllCategories() {
        return List.of();
    }
}
