package com.academy.dream_shop.services.product.category;

import com.academy.dream_shop.exceptions.CategoryNotFound;
import com.academy.dream_shop.exceptions.ProductNotFoundException;
import com.academy.dream_shop.models.Category;
import com.academy.dream_shop.models.Product;
import com.academy.dream_shop.repository.CategoryRepository;
import com.academy.dream_shop.repository.ProductRepository;
import com.academy.dream_shop.request.CategoryUpdateRequest;
import com.academy.dream_shop.request.ProductRequest;
import com.academy.dream_shop.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

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
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFound("Category not found"));

        category.setName(req.getName());

        if(req.getProduct() != null){
            category.setProduct(req.getProduct());
        }

        return categoryRepository.save(category);
    }
    


    @Override
    public void deleteCategoryId(Long id) {
        categoryRepository.findById(id).ifPresentOrElse(categoryRepository::delete , () -> {
            throw new CategoryNotFound("Category does not exist");
        } );
        

    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}


