package com.ecommerce.ecommercebackend.services;

import com.ecommerce.ecommercebackend.models.Category;
import com.ecommerce.ecommercebackend.models.Inventory;
import com.ecommerce.ecommercebackend.models.Product;
import com.ecommerce.ecommercebackend.models.Seller;
import com.ecommerce.ecommercebackend.repositories.CategoryRepository;
import com.ecommerce.ecommercebackend.repositories.InventoryRepository;
import com.ecommerce.ecommercebackend.repositories.ProductRepository;
import com.ecommerce.ecommercebackend.repositories.SellerRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;
    private final SellerRepository sellerRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, InventoryRepository inventoryRepository, SellerRepository sellerRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
        this.sellerRepository = sellerRepository;
        this.categoryRepository = categoryRepository;
    }

    public Product listProduct(String name, String description, String category, String unitsOfMeasure, Double price,  Long sellerId,Double quantity) {
//        Verify if seller exists
        Optional<Seller> sellerOptional = sellerRepository.findById(sellerId);
        if(sellerOptional.isEmpty()){
            throw new RuntimeException("Seller Not Registered. Please Signup At Sellers Portal!!");
        }
        Seller seller = sellerOptional.get();

        Optional<Inventory> inventoryOptional  = inventoryRepository.findByProduct_Name(name);
        //verify if the seller is posting same product again and again!!
        if(inventoryOptional.isPresent() && inventoryOptional.get().getProduct().getSeller().getId()==sellerId){
            throw new RuntimeException("Product Already Listed By the seller!!");
        }

        Optional<Category> categoryOptional = categoryRepository.findByName(category);
        if(categoryOptional.isEmpty()){
            throw new RuntimeException("Category Not Registered. Please Contact Admin!!");
        }

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setCategory(categoryOptional.get());
        product.setUnitOfMeasure(unitsOfMeasure);
        product.setSeller(seller);
        product.setPrice(price);
        Product savedProduct = productRepository.save(product);

        Inventory inventory = new Inventory();
        inventory.setProduct(product);
        inventory.setQuantity(quantity);
        inventoryRepository.save(inventory);
        return savedProduct;

    }

}
