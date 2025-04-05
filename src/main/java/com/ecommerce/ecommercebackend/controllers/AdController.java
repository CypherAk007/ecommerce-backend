package com.ecommerce.ecommercebackend.controllers;

import com.ecommerce.ecommercebackend.dtos.AdRequestDTO;
import com.ecommerce.ecommercebackend.dtos.AdResponseDTO;
import com.ecommerce.ecommercebackend.dtos.ResponseStatus;
import com.ecommerce.ecommercebackend.models.Advertisements;
import com.ecommerce.ecommercebackend.services.AdService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdController {
    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    @PostMapping("/admin/advertisements")
    public ResponseEntity<AdResponseDTO> createAds(AdRequestDTO adRequestDTO){
        AdResponseDTO adResponseDTO = new AdResponseDTO();
        try {
            Advertisements advertisement = adService.createAds(adRequestDTO.getProductId());
            adResponseDTO.setAdId(advertisement.getId());
            adResponseDTO.setMessage("Advertisement Created Successfully!!");
            adResponseDTO.setStatus(ResponseStatus.SUCCESS);
        } catch (Exception e) {
            adResponseDTO.setStatus(ResponseStatus.FAILURE);
            adResponseDTO.setMessage("Failed To Create AD : "+e.getMessage());
        }
        return ResponseEntity.ok(adResponseDTO);
    }

    @GetMapping("/users/advertisements")
    public ResponseEntity<?> createAds(@RequestParam Long userId){

        try {
            List<Advertisements> advertisements = adService.getReleaventAds(userId);
            return ResponseEntity.ok(advertisements);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed To fetch AD : "+e.getMessage());
        }
    }
}
