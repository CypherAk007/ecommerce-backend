package com.ecommerce.ecommercebackend.repositories;

import com.ecommerce.ecommercebackend.models.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    Optional<Inventory> findByProduct_Name(String name);
    List<Inventory> findByProduct_Seller_Id(Long id);
}
