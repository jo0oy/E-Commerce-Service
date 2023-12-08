package com.example.ecommerce.infrastructure.item;

import com.example.ecommerce.domain.item.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemEntityRepository extends JpaRepository<ItemEntity, Long> {
}
