package com.ecommerce.ecommercebackend.controllers;

import com.ecommerce.ecommercebackend.models.Inventory;
import com.ecommerce.ecommercebackend.repositories.InventoryRepository;
import com.ecommerce.ecommercebackend.services.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/seller/{sellerId}/inventory")
    public ResponseEntity<?> getInventoryBySeller(@PathVariable Long sellerId){
        try {
            List<Inventory> inventoryList = inventoryService.getInventoryBySeller(sellerId);
            return ResponseEntity.ok(inventoryList);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
