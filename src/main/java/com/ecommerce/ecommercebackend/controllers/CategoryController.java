package com.ecommerce.ecommercebackend.controllers;

import com.ecommerce.ecommercebackend.models.Category;
import com.ecommerce.ecommercebackend.services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/store-category")
    public ResponseEntity<?> storeCategory(@RequestBody String name){
        try {
            Category category = categoryService.storeCategory(name);
            return ResponseEntity.ok(category.getName()+" Stored Successfully!!!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(name+" Storage Failed due to "+e.getMessage());
        }
    }
}
