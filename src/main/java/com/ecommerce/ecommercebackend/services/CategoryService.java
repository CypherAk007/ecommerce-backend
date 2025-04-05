package com.ecommerce.ecommercebackend.services;

import com.ecommerce.ecommercebackend.models.Category;
import com.ecommerce.ecommercebackend.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;

    }

    public Category storeCategory(String name){
        if(categoryRepository.findByName(name).isPresent()){
            throw new RuntimeException("Category Already present!!");
        }
        Category category = new Category();
        category.setName(name);
        return categoryRepository.save(category);
    }
}
