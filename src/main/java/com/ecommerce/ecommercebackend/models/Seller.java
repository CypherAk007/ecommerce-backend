package com.ecommerce.ecommercebackend.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@DiscriminatorValue("SELLER")
public class Seller extends User{
    @OneToMany
    private List<Product> listedProducts;
}
