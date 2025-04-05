package com.ecommerce.ecommercebackend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Notification extends BaseModel{
    @ManyToOne
    private Product product;
    @ManyToMany
    private List<Customer> subscribedUsers;
}
