package com.ecommerce.ecommercebackend.repositories;

import com.ecommerce.ecommercebackend.models.Advertisements;
import com.ecommerce.ecommercebackend.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdRepository extends JpaRepository<Advertisements,Long> {
    List<Advertisements> findAllByCategory(Category category);
}
