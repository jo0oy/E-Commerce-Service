package com.example.ecommerce.domain.item.dto;

import com.example.ecommerce.domain.item.entity.ItemEntity;

public record RegisterItemCommand(
     String itemName,
     Integer itemPrice,
     Integer stockQuantity,
     ItemEntity.Status status
) {
    public ItemEntity toEntity() {
        return ItemEntity.builder()
                .itemName(itemName)
                .itemPrice(itemPrice)
                .stockQuantity(stockQuantity)
                .status(status)
                .build();
    }
}
