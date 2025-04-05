package com.ecommerce.ecommercebackend.dtos;

import lombok.Data;

@Data
public class AddToCartRequestDTO {
    private Long userId;
    private Double quantity;
}
