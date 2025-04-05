package com.ecommerce.ecommercebackend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Advertisements extends BaseModel{
    private String productName;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Product product;
    private Long clicks;
}
