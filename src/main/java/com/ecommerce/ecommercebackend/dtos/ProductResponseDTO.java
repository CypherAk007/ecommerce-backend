package com.ecommerce.ecommercebackend.dtos;

import lombok.Data;

@Data
public class ProductResponseDTO {
    private String message;
    private ResponseStatus status;
    private Long productId;
}
