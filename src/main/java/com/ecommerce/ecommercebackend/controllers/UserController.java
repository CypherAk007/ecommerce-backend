package com.ecommerce.ecommercebackend.controllers;

import com.ecommerce.ecommercebackend.dtos.*;
import com.ecommerce.ecommercebackend.models.Customer;
import com.ecommerce.ecommercebackend.models.Seller;
import com.ecommerce.ecommercebackend.models.User;
import com.ecommerce.ecommercebackend.services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/customer/signup")
    @Tag(name = "Authentication", description = "APIs for user authentication")
    public ResponseEntity<SignupResponseDTO> signupCustomer(@RequestBody SignupRequestDTO requestDTO) {
        SignupResponseDTO signupResponseDTO = new SignupResponseDTO();
        try {
            Customer customer = userService.signupCustomer(requestDTO.getName(), requestDTO.getEmail(), requestDTO.getPassword());
            signupResponseDTO.setMessage("Customer Registration Successful!!");
            signupResponseDTO.setUserId(customer.getId());
            signupResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            signupResponseDTO.setResponseStatus(ResponseStatus.FAILURE);
            signupResponseDTO.setMessage(e.getMessage());
        }
        return ResponseEntity.ok(signupResponseDTO);
    }

    @PostMapping("/customer/login")
    @Tag(name = "Authentication", description = "APIs for user authentication")
    public ResponseEntity<LoginResponseDTO> loginCustomer(@RequestBody LoginRequestDTO requestDTO) {
        LoginResponseDTO responseDTO = new LoginResponseDTO();
        try{
            Customer customer = userService.loginCustomer(requestDTO.getEmail(),requestDTO.getPassword());
            responseDTO.setUserId(customer.getId());
            responseDTO.setMessage("User Login Successful!!");
            responseDTO.setStatus(ResponseStatus.SUCCESS);
            return ResponseEntity.ok(responseDTO);
        }catch (Exception e){
            responseDTO.setMessage(e.getMessage());
            responseDTO.setStatus(ResponseStatus.FAILURE);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDTO);
        }

    }

    @PostMapping("/seller/signup")
    @Tag(name = "Authentication", description = "APIs for user authentication")
    public ResponseEntity<SignupResponseDTO> signupSeller(@RequestBody SignupRequestDTO requestDTO) {
        SignupResponseDTO signupResponseDTO = new SignupResponseDTO();
        try {
            Seller seller = userService.signupSeller(requestDTO.getName(), requestDTO.getEmail(), requestDTO.getPassword());
            signupResponseDTO.setMessage("Seller Registration Successful!!");
            signupResponseDTO.setUserId(seller.getId());
            signupResponseDTO.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            signupResponseDTO.setResponseStatus(ResponseStatus.FAILURE);
            signupResponseDTO.setMessage(e.getMessage());
        }
        return ResponseEntity.ok(signupResponseDTO);
    }

    @PostMapping("/seller/login")
    @Tag(name = "Authentication", description = "APIs for user authentication")
    public ResponseEntity<LoginResponseDTO> loginSeller(@RequestBody LoginRequestDTO requestDTO) {
        LoginResponseDTO responseDTO = new LoginResponseDTO();
        try{
            Seller seller = userService.loginSeller(requestDTO.getEmail(),requestDTO.getPassword());
            responseDTO.setUserId(seller.getId());
            responseDTO.setMessage("Seller Login Successful!!");
            responseDTO.setStatus(ResponseStatus.SUCCESS);
            return ResponseEntity.ok(responseDTO);
        }catch (Exception e){
            responseDTO.setMessage(e.getMessage());
            responseDTO.setStatus(ResponseStatus.FAILURE);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseDTO);
        }

    }
}