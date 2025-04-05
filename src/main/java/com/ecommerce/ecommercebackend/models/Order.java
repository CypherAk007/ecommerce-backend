package com.ecommerce.ecommercebackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "customer_order")
public class Order extends BaseModel{
    @ManyToMany
    private List<Product> products;
    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer buyer;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
