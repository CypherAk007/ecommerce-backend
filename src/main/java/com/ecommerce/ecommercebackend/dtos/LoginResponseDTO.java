package com.ecommerce.ecommercebackend.dtos;

import lombok.Data;

@Data
public class LoginResponseDTO {
    private String message;
    private ResponseStatus status;
    private Long userId;
}
