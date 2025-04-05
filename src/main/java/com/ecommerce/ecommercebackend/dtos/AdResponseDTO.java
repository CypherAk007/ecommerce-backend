package com.ecommerce.ecommercebackend.dtos;

import lombok.Data;

@Data
public class AdResponseDTO {
    private Long adId;
    private String message;
    private ResponseStatus status;
}
