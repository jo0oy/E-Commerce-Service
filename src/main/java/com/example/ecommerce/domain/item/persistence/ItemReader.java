package com.example.ecommerce.domain.item.persistence;

import com.example.ecommerce.domain.item.entity.ItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ItemReader {
    ItemEntity findById(Long id);

    List<ItemEntity> findAll();

    Page<ItemEntity> findAll(Pageable pageable);
}
