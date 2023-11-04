package com.example.ecommerce.domain.item.persistence;

import com.example.ecommerce.config.DatabaseCleanAfterEach;
import com.example.ecommerce.domain.item.entity.ItemEntity;
import com.example.ecommerce.infrastructure.item.ItemEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DatabaseCleanAfterEach
@SpringBootTest
class ItemReaderTest {

    @Autowired
    private ItemEntityRepository itemEntityRepository;

    @Autowired
    private ItemReader itemReader;

    @BeforeEach
    void setUp() {
        for (int i = 1; i <= 5; i++) {
            itemEntityRepository.save(
                    ItemEntity.builder()
                            .itemName("item_" + i)
                            .itemPrice(10000 + i * 1000)
                            .stockQuantity(100 + i * 10)
                            .build()
            );
        }
    }

    @Test
    void findById() {
        // given
        var itemId = 1L;

        // when
        ItemEntity itemEntity = itemReader.findById(itemId);

        // then
        assertThat(itemEntity.getId()).isEqualTo(itemId);
        assertThat(itemEntity.isDeleted()).isFalse();
    }

    @Test
    void findAll() {
        // when
        List<ItemEntity> result = itemReader.findAll();

        // then
        assertThat(result.size()).isEqualTo(5);
    }
}