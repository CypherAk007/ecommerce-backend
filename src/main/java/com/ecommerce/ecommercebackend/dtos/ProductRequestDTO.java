package com.ecommerce.ecommercebackend.dtos;

import com.ecommerce.ecommercebackend.models.Category;
import com.ecommerce.ecommercebackend.models.Seller;
import lombok.Data;

@Data
public class ProductRequestDTO {
    private String name;
    private String category;
    private String unitsOfMeasure;
    private String description;
    private Double price;
    private Long sellerId;
    private Double Quantity;

}
