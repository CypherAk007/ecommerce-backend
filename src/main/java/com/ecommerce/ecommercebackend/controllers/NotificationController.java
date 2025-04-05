package com.ecommerce.ecommercebackend.controllers;

import com.ecommerce.ecommercebackend.models.Notification;
import com.ecommerce.ecommercebackend.services.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/products/{productId}/subscribe")
    public ResponseEntity<?> subscribeToProduct(@PathVariable Long productId,@RequestBody Long userId){
        try{
        Notification notification = notificationService.subscribe(productId,userId);
            return ResponseEntity.ok(notification.getProduct().getName()+" Subscribed!!!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/products/{productId}/unsubscribe")
    public ResponseEntity<?> unSubscribeToProduct(@PathVariable Long productId,@RequestBody Long userId){
        try{
            Notification notification = notificationService.unSubscribe(productId,userId);
            return ResponseEntity.ok(notification.getProduct().getName()+" UnSubscribed!!!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/admin/products/{productId}/notify")
    public ResponseEntity<?> notifyProductStock(@PathVariable Long productId){
        try{
            notificationService.pushNotification(productId);
            return ResponseEntity.ok("Notified Users!!!");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

}
