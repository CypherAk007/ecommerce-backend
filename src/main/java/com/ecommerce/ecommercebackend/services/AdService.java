package com.ecommerce.ecommercebackend.services;

import com.ecommerce.ecommercebackend.models.*;
import com.ecommerce.ecommercebackend.repositories.AdRepository;
import com.ecommerce.ecommercebackend.repositories.CustomerRepository;
import com.ecommerce.ecommercebackend.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdService {
    private final AdRepository adRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public AdService(AdRepository adRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.adRepository = adRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public List<Advertisements> getReleaventAds(Long userId) {
        Optional<Customer> userOptional = customerRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new RuntimeException("Invalid User!!");
        }

        Customer customer = userOptional.get();
        List<Category> userCategory = customer.getInterestedCategories();
        List<Advertisements> userAds= new ArrayList<>();
        for(Category category: userCategory){
            userAds.addAll(adRepository.findAllByCategory(category));
        }
        return userAds;

    }

    public Advertisements createAds(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isEmpty()){
            throw new RuntimeException("Invalid Product!!");
        }
        Product product = productOptional.get();
        Advertisements advertisements = new Advertisements();
        advertisements.setCategory(product.getCategory());
        advertisements.setProduct(product);
        advertisements.setProductName(product.getName());
        return adRepository.save(advertisements);
    }
}
