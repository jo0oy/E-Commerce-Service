package com.example.ecommerce.domain.item.persistence;

import com.example.ecommerce.domain.item.entity.ItemEntity;

public interface ItemDeleter {

    void delete(ItemEntity item);
}
