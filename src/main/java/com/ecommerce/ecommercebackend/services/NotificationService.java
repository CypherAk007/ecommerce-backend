package com.ecommerce.ecommercebackend.services;

import com.ecommerce.ecommercebackend.models.Customer;
import com.ecommerce.ecommercebackend.models.Notification;
import com.ecommerce.ecommercebackend.models.Product;
import com.ecommerce.ecommercebackend.models.User;
import com.ecommerce.ecommercebackend.repositories.CustomerRepository;
import com.ecommerce.ecommercebackend.repositories.NotificationRepository;
import com.ecommerce.ecommercebackend.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public NotificationService(NotificationRepository notificationRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.notificationRepository = notificationRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public Notification subscribe(Long productId,Long userId){
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isEmpty()){
            throw new RuntimeException("Unable to Find Product For Subscription");
        }

        Optional<Customer> userOptional = customerRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new RuntimeException("Unable to Find User For Subscription");
        }

//        check if user already subscribed to product
        boolean isSubscribed =  notificationRepository.existsByCustomerIdAndProductId(userId,productId);
        if(isSubscribed){
            throw new RuntimeException("User Already Subscribed to the Product");
        }

        Optional<Notification> notificationOptional = notificationRepository.findByProductId(productId);
        Notification notification;
        if(notificationOptional.isEmpty()){
             notification = new Notification();
            notification.setProduct(productOptional.get());
            notification.setSubscribedUsers(new ArrayList<>(List.of(userOptional.get())));
        }else{
             notification = notificationOptional.get();
             notification.getSubscribedUsers().add(userOptional.get());
        }
        return notificationRepository.save(notification);
    }

    public Notification unSubscribe(Long productId,Long userId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isEmpty()){
            throw new RuntimeException("Unable to Find Product For Subscription");
        }

        Optional<Customer> userOptional = customerRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new RuntimeException("Unable to Find User For Subscription");
        }

//        check if user already subscribed to product
        boolean isSubscribed =  notificationRepository.existsByCustomerIdAndProductId(userId,productId);
        if(!isSubscribed){
            throw new RuntimeException("User Has not Subscribed to the Product");
        }

        Optional<Notification> notificationOptional = notificationRepository.findByProductId(productId);
        if(notificationOptional.isEmpty()){
            throw new RuntimeException("User Has not Subscribed to the Product");
        }
        Notification notification = notificationOptional.get();
        notification.getSubscribedUsers().remove(userOptional.get());
        return notificationRepository.save(notification);
    }

    public void pushNotification(Long productId) {
        Optional<Notification> notificationOptional = notificationRepository.findByProductId(productId);
        if(notificationOptional.isPresent()){
            // **send email**/Notification to user
            Notification notification = notificationOptional.get();
            List<Customer> customers = notification.getSubscribedUsers();
            customers.stream().forEach(customer -> System.out.println("Hello "+customer.getName()+"!! "+notification.getProduct().getName()+" Is Available Now!!"));
        }
    }
}
