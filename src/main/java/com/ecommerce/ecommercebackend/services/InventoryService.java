package com.ecommerce.ecommercebackend.services;

import com.ecommerce.ecommercebackend.models.Inventory;
import com.ecommerce.ecommercebackend.repositories.InventoryRepository;
import com.ecommerce.ecommercebackend.repositories.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final SellerRepository sellerRepository;

    public InventoryService(InventoryRepository inventoryRepository, SellerRepository sellerRepository) {
        this.inventoryRepository = inventoryRepository;
        this.sellerRepository = sellerRepository;
    }

    public List<Inventory> getInventoryBySeller(Long sellerId){
        if(sellerRepository.findById(sellerId).isEmpty()){
            throw new RuntimeException("Invalid Seller!!");
        }
        List<Inventory> inventoryList = inventoryRepository.findByProduct_Seller_Id(sellerId);
        return inventoryList;
    }
}
