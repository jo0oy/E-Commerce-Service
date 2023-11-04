package com.example.ecommerce.domain.item.dto;

import com.example.ecommerce.domain.item.entity.ItemEntity;

public record ChangeItemStatusCommand(
        ItemEntity.Status status
) {
}
