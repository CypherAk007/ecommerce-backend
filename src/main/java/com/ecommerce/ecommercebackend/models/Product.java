package com.ecommerce.ecommercebackend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product extends BaseModel{
    private String name;
    @ManyToOne
    private Category category;
    private String unitOfMeasure;// 1unit = 1laptop -> grams,kg,liters...
    private String url;
    private Double rating;
    private String description;
    private String imageUrl;
    private Double price;
    @ManyToOne
    @JoinColumn(name = "seller")
    private Seller seller;
}
