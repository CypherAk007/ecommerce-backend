package com.ecommerce.ecommercebackend.dtos;

import lombok.Data;

@Data
public class InventoryQuantityUpdateRequestDTO {
    private Long productId;
    private Double quantity;
}
