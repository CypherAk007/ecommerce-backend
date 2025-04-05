package com.ecommerce.ecommercebackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@DiscriminatorValue("CUSTOMER")
public class Customer extends User{
    @ManyToMany
    private List<Product> cart;

    @ManyToMany
    private List<Category> interestedCategories;
    @OneToMany
    private List<Order> previousOrders;

}
