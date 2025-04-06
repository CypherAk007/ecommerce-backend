package com.ecommerce.ecommercebackend.controllers;

import com.ecommerce.ecommercebackend.dtos.InventoryQuantityUpdateRequestDTO;
import com.ecommerce.ecommercebackend.dtos.InventoryQuantityUpdateResponseDTO;
import com.ecommerce.ecommercebackend.dtos.ResponseStatus;
import com.ecommerce.ecommercebackend.models.Inventory;
import com.ecommerce.ecommercebackend.repositories.InventoryRepository;
import com.ecommerce.ecommercebackend.services.InventoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/seller/inventory/update-stock")
    public ResponseEntity<InventoryQuantityUpdateResponseDTO> updateInventory(@RequestBody InventoryQuantityUpdateRequestDTO requestDTO){
        InventoryQuantityUpdateResponseDTO responseDTO = new InventoryQuantityUpdateResponseDTO();
        try {
            inventoryService.updateStock(requestDTO.getProductId(),requestDTO.getQuantity());
            responseDTO.setMessage("Inventory Updated!!");
            responseDTO.setStatus(ResponseStatus.SUCCESS);

            return ResponseEntity.ok(responseDTO);
        }catch (Exception e){
            responseDTO.setStatus(ResponseStatus.FAILURE);
            responseDTO.setMessage(e.getMessage());
            return ResponseEntity.internalServerError().body(responseDTO);
        }
    }
}
