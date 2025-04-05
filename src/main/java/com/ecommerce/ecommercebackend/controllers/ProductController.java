package com.ecommerce.ecommercebackend.controllers;

import com.ecommerce.ecommercebackend.dtos.ProductRequestDTO;
import com.ecommerce.ecommercebackend.dtos.ProductResponseDTO;
import com.ecommerce.ecommercebackend.dtos.ResponseStatus;
import com.ecommerce.ecommercebackend.models.Product;
import com.ecommerce.ecommercebackend.services.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/seller/list-product")
    @Tag(name = "Product Listing", description = "APIs for seller Listing Products!!")
    public ResponseEntity<ProductResponseDTO> listProduct(@RequestBody ProductRequestDTO requestDTO){
        ProductResponseDTO responseDTO = new ProductResponseDTO();
        try {
            Product product = productService.listProduct(requestDTO.getName(),requestDTO.getDescription(),requestDTO.getCategory(),requestDTO.getUnitsOfMeasure(),requestDTO.getPrice(),requestDTO.getSellerId(),requestDTO.getQuantity());
            responseDTO.setProductId(product.getId());
            responseDTO.setMessage("Product Listed Successfully!!");
            responseDTO.setStatus(ResponseStatus.SUCCESS);
            return ResponseEntity.ok(responseDTO);
        }catch (Exception e){
            responseDTO.setMessage("Product Listing Failed!!"+e.getMessage());
            responseDTO.setStatus(ResponseStatus.FAILURE);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
    }
}