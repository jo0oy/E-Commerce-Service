package com.example.ecommerce.domain.item.dto;

public record UpdateItemInfoCommand(
        String itemName,
        Integer itemPrice,
        Integer stockQuantity
) {
}
