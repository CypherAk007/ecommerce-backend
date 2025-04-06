package com.ecommerce.ecommercebackend.services;

import com.ecommerce.ecommercebackend.components.UserStockNotificationObserver;
import com.ecommerce.ecommercebackend.models.Inventory;
import com.ecommerce.ecommercebackend.models.Product;
import com.ecommerce.ecommercebackend.repositories.InventoryRepository;
import com.ecommerce.ecommercebackend.repositories.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final SellerRepository sellerRepository;
    private final UserStockNotificationObserver userStockNotificationObserver; // Inject the observer


    public InventoryService(InventoryRepository inventoryRepository, SellerRepository sellerRepository, UserStockNotificationObserver userStockNotificationObserver) {
        this.inventoryRepository = inventoryRepository;
        this.sellerRepository = sellerRepository;
        this.userStockNotificationObserver = userStockNotificationObserver;
    }

    public List<Inventory> getInventoryBySeller(Long sellerId){
        if(sellerRepository.findById(sellerId).isEmpty()){
            throw new RuntimeException("Invalid Seller!!");
        }
        List<Inventory> inventoryList = inventoryRepository.findByProduct_Seller_Id(sellerId);
        return inventoryList;
    }

    public void updateStock(Long productId, Double newQuantity) {
        Optional<Inventory> inventoryOptional = inventoryRepository.findByProductId(productId);
        inventoryOptional.ifPresent(inventory -> {
            System.out.println("Inside Update stock inventory service!!");
//            The most probable reason is that the Inventory object loaded during the updateStock() call is a different instance than the one where you registered the observer during the subscribe() call. Since the observers list is transient, the new Inventory instance will not have any observers registered.
//                    How to Fix
//            You need to ensure that the UserStockNotificationObserver is registered with the correct Inventory instance before updateStock() is called and the quantity becomes greater than zero.
//                    Here are a few approaches to address this:
//            Approach 1: Register Observer Every Time Inventory is Loaded (Less Efficient but Simpler for this Example):
//            Modify your InventoryService.updateStock() method to fetch the Inventory and re-register the observer before updating the quantity:
            inventory.addObserver(userStockNotificationObserver);


            inventory.setQuantity(newQuantity); // This will notify observers if quantity > 0
            inventoryRepository.save(inventory);
        });
    }

}
