package com.ecommerce.ecommercebackend.dtos;

import lombok.Data;

@Data
public class SignupResponseDTO {
    private Long userId;
    private String message;
    private ResponseStatus responseStatus;
}
