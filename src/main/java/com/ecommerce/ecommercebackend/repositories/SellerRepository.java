package com.ecommerce.ecommercebackend.repositories;

import com.ecommerce.ecommercebackend.models.Customer;
import com.ecommerce.ecommercebackend.models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller,Long> {
    Optional<Seller> findByEmail(String email);
}
