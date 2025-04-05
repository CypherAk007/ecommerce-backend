package com.ecommerce.ecommercebackend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Inventory extends BaseModel{
    @OneToOne
    private Product product;
    private Long quantity;//current stock
}
