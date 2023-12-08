package com.example.ecommerce.infrastructure.item;

import com.example.ecommerce.domain.item.entity.ItemEntity;
import com.example.ecommerce.domain.item.persistence.ItemDeleter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ItemDeleterImpl implements ItemDeleter {

    private final ItemEntityRepository itemEntityRepository;

    @Override
    public void delete(ItemEntity item) {
        itemEntityRepository.delete(item);
    }
}
