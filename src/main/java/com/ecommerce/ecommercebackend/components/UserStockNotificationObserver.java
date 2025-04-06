package com.ecommerce.ecommercebackend.components;

import com.ecommerce.ecommercebackend.models.Customer;
import com.ecommerce.ecommercebackend.models.Notification;
import com.ecommerce.ecommercebackend.models.Product;
import com.ecommerce.ecommercebackend.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserStockNotificationObserver implements StockUpdateObserver{
    @Lazy
    @Autowired
    private  NotificationService notificationService;

//    public UserStockNotificationObserver(NotificationService notificationService) {
//        this.notificationService = notificationService;
//    }

    @Override
    public void onStockAvailable(Product product) {
        List<Notification> notifications = notificationService.findByProduct(product);
        if(notifications!=null && !notifications.isEmpty()){
            Notification notification = notifications.get(0);
            List<Customer> customersToNotify = notification.getSubscribedUsers();
            if(customersToNotify!=null && !customersToNotify.isEmpty()){
                for(Customer customer:customersToNotify){
                    // Implement your actual notification sending mechanism here
                    System.out.println("Sending notification to " + customer.getEmail() + " for product: " + product.getName());
                    // You might want to use an email service, push notification service, etc.
                }
                notificationService.delete(notification);
            }
        }
    }
}
