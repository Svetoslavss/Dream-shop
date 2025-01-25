package com.academy.dream_shop.services.product.category;

import com.academy.dream_shop.exceptions.AlreadyExistingException;
import com.academy.dream_shop.exceptions.ResourceNotFound;
import com.academy.dream_shop.models.Category;
import com.academy.dream_shop.models.Product;
import com.academy.dream_shop.repository.CategoryRepository;
import com.academy.dream_shop.repository.ProductRepository;
import com.academy.dream_shop.request.CategoryUpdateRequest;
import com.academy.dream_shop.request.ProductRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService{

    private CategoryRepository categoryRepository;
  
    private ProductRepository productRepository;

    @Override
    public Category addCategory(Category category) {
        return Optional.of(category).filter(c -> !categoryRepository.existsById(c.getId()))
                .map(categoryRepository::save)
                .orElseThrow(() -> new AlreadyExistingException(category.getName() + "Already exists"));
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Category not found"));
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        return categoryRepository.findByName(categoryName);
    }


    @Override
    public Category updateCategory(CategoryUpdateRequest req, Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFound("Category not found"));

        category.setName(req.getName());

        if(req.getProduct() != null){
            category.setProduct(req.getProduct());
        }


        return categoryRepository.save(category);
    }
    


    @Override
    public void deleteCategoryId(Long id) {
        categoryRepository.findById(id).ifPresentOrElse(categoryRepository::delete , () -> {
            throw new ResourceNotFound("Category does not exist");
        } );
        

    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}


