package com.ecommerce.ecommercebackend.services;

import com.ecommerce.ecommercebackend.models.*;
import com.ecommerce.ecommercebackend.repositories.*;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;
    private final SellerRepository sellerRepository;
    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public ProductService(ProductRepository productRepository, InventoryRepository inventoryRepository, SellerRepository sellerRepository, CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
        this.sellerRepository = sellerRepository;
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
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

    public Product addToCart(Long userId,Long productId,Double quantity) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isEmpty()){
            throw new RuntimeException("Product Not Found!!");
        }
        Product product = productOptional.get();
        Optional<Inventory> inventoryOptional = inventoryRepository.findByProduct(product);
        if(inventoryOptional.isEmpty()){
            throw new RuntimeException("Product not In Inventory!!");
        }
        Inventory productInventory = inventoryOptional.get();
        if(productInventory.getQuantity()<quantity){
            throw new RuntimeException(String.format("Product Quantity Higher than Inventory Stock Please Select Quantity <= !!",productInventory.getQuantity()));
        }

        Optional<Customer> customerOptional = customerRepository.findById(userId);
        if(customerOptional.isEmpty()){
            throw new RuntimeException("Invalid User!!");
        }
        productInventory.setQuantity(productInventory.getQuantity()-quantity);
        inventoryRepository.save(productInventory);

        Customer customer = customerOptional.get();
        customer.getCart().add(product);
        customer.getInterestedCategories().add(product.getCategory());
        customerRepository.save(customer);
        return product;

    }
}
