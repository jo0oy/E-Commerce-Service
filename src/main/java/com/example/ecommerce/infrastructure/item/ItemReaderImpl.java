package com.example.ecommerce.infrastructure.item;

import com.example.ecommerce.common.exception.EntityNotFoundException;
import com.example.ecommerce.domain.item.entity.ItemEntity;
import com.example.ecommerce.domain.item.persistence.ItemReader;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ItemReaderImpl implements ItemReader {

    private final ItemEntityRepository itemEntityRepository;

    @Override
    public ItemEntity findById(Long id) {
        return itemEntityRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<ItemEntity> findAll() {
        return itemEntityRepository.findAll();
    }

    @Override
    public Page<ItemEntity> findAll(Pageable pageable) {
        return itemEntityRepository.findAll(pageable);
    }
}
