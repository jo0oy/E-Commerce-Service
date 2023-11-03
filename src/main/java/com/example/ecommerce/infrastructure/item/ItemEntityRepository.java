package com.example.ecommerce.infrastructure.item;

import com.example.ecommerce.domain.item.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemEntityRepository extends JpaRepository<ItemEntity, Long> {

    Optional<ItemEntity> findByIdAndDeletedIsFalse(Long id);

    List<ItemEntity> findAllByDeletedIsFalse();
}
