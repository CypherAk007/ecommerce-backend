package com.ecommerce.ecommercebackend.services;

import com.ecommerce.ecommercebackend.models.Customer;
import com.ecommerce.ecommercebackend.models.Seller;
import com.ecommerce.ecommercebackend.repositories.CustomerRepository;
import com.ecommerce.ecommercebackend.repositories.SellerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final CustomerRepository customerRepository;
    private final SellerRepository sellerRepository;

    public UserService(CustomerRepository customerRepository, SellerRepository sellerRepository) {
        this.customerRepository = customerRepository;
        this.sellerRepository = sellerRepository;
    }

    public Customer signupCustomer(String name,String email, String password){
        if(customerRepository.findByEmail(email).isPresent()){
            throw new RuntimeException("User With Email Already Exists!!");
        }
        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        customer.setPassword(bCryptPasswordEncoder.encode(password));
        return customerRepository.save(customer);
    }

    public Customer loginCustomer(String email, String password) {
        Customer customer = customerRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User Not Found!!"));
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(passwordEncoder.matches(password,customer.getPassword())){
            return customer;
        }
        throw new RuntimeException("Invalid Credentials!!");
    }


    public Seller signupSeller(String name,String email, String password){
        if(sellerRepository.findByEmail(email).isPresent()){
            throw new RuntimeException("User With Email Already Exists!!");
        }
        Seller seller = new Seller();
        seller.setName(name);
        seller.setEmail(email);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        seller.setPassword(bCryptPasswordEncoder.encode(password));
        return sellerRepository.save(seller);
    }

    public Seller loginSeller(String email, String password) {
        Seller seller = sellerRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User Not Found!!"));
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(passwordEncoder.matches(password,seller.getPassword())){
            return seller;
        }
        throw new RuntimeException("Invalid Credentials!!");
    }
}
