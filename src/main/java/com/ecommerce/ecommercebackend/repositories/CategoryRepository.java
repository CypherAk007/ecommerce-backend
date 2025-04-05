package com.ecommerce.ecommercebackend.repositories;

import com.ecommerce.ecommercebackend.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByName(String name);
}
