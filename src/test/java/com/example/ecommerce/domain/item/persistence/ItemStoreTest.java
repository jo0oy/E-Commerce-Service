package com.example.ecommerce.domain.item.persistence;

import com.example.ecommerce.config.DatabaseCleanAfterEach;
import com.example.ecommerce.domain.item.entity.ItemEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;


@DatabaseCleanAfterEach
@SpringBootTest
class ItemStoreTest {

    @Autowired
    private ItemStore itemStore;

    @Test
    void store_성공_테스트() {
        //given
        var item = ItemEntity.builder()
            .itemName("itemName")
            .itemPrice(10000)
            .status(ItemEntity.Status.PREPARE)
            .stockQuantity(100)
            .build();

        //when
        Long storedResult = itemStore.store(item);

        //then
        assertThat(storedResult).isEqualTo(1);
    }
}