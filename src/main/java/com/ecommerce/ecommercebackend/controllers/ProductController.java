package com.ecommerce.ecommercebackend.controllers;

import com.ecommerce.ecommercebackend.dtos.*;
import com.ecommerce.ecommercebackend.dtos.ResponseStatus;
import com.ecommerce.ecommercebackend.models.Product;
import com.ecommerce.ecommercebackend.services.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/product/{productId}/addToCart")
    @Tag(name = "Add to Cart", description = "APIs for Products Add to Cart!!")
    public ResponseEntity<AddToCartResponseDTO> addProductToCart(@PathVariable Long productId, @RequestBody AddToCartRequestDTO requestDTO){
        AddToCartResponseDTO responseDTO = new AddToCartResponseDTO();
        try {
            Product product = productService.addToCart(requestDTO.getUserId(),productId,requestDTO.getQuantity());
            responseDTO.setProductId(product.getId());
            responseDTO.setMessage("Added To Cart Successfully!!");
            responseDTO.setStatus(ResponseStatus.SUCCESS);
            return ResponseEntity.ok(responseDTO);
        }catch (Exception e){
            responseDTO.setMessage("Product Listing Failed!!"+e.getMessage());
            responseDTO.setStatus(ResponseStatus.FAILURE);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseDTO);
        }
    }
}