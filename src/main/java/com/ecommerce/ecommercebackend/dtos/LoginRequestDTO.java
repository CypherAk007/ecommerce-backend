package com.ecommerce.ecommercebackend.dtos;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String email;
    private String password;

}
