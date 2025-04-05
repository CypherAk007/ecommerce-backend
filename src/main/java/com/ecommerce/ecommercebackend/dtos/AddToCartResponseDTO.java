package com.ecommerce.ecommercebackend.dtos;

import lombok.Data;

@Data
public class AddToCartResponseDTO {
    private String message;
    private ResponseStatus status;
    private Long productId;
}
