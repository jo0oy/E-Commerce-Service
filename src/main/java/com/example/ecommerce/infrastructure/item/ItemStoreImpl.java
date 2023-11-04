package com.example.ecommerce.infrastructure.item;

import com.example.ecommerce.domain.item.entity.ItemEntity;
import com.example.ecommerce.domain.item.persistence.ItemStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ItemStoreImpl implements ItemStore {

    private final ItemEntityRepository itemRepository;

    @Override
    public Long store(ItemEntity entity) {
        return itemRepository.save(entity).getId();
    }
}
