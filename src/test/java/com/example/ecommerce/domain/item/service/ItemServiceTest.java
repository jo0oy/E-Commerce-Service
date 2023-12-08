package com.example.ecommerce.domain.item.service;

import com.example.ecommerce.config.DatabaseCleanAfterEach;
import com.example.ecommerce.domain.item.dto.ChangeItemStatusCommand;
import com.example.ecommerce.domain.item.dto.RegisterItemCommand;
import com.example.ecommerce.domain.item.dto.UpdateItemInfoCommand;
import com.example.ecommerce.domain.item.entity.ItemEntity;
import com.example.ecommerce.infrastructure.item.ItemEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DatabaseCleanAfterEach
@SpringBootTest
class ItemServiceTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemEntityRepository itemEntityRepository;

    private List<ItemEntity> items;

    @BeforeEach
    void setUp() {
        items = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            items.add(
                itemEntityRepository.save(
                    ItemEntity.builder()
                        .itemName("item" + i)
                        .itemPrice(10000 + (i * 1000))
                        .status(ItemEntity.Status.ON_SALES)
                        .stockQuantity(100 + i * 10)
                        .build()
                )
            );
        }
    }

    @Test
    void registerItem_성공_테스트() {
        // given
        var itemName = "itemName";
        var price = 15000;
        var status = ItemEntity.Status.PREPARE;
        var quantity = 1000;
        var itemRequest = new RegisterItemCommand(itemName, price, quantity, status);

        // when
        Long registerItem = itemService.registerItem(itemRequest);

        // then
        assertThat(registerItem).isEqualTo(items.size() + 1);
    }

    @Test
    void changeStatus_성공_테스트() {
        // given
        var targetId = 1L;
        var updateStatus = ItemEntity.Status.END_OF_SALES;
        var request = new ChangeItemStatusCommand(updateStatus);

        // when
        itemService.changeStatus(targetId, request);

        // then
        Optional<ItemEntity> updatedItem = itemEntityRepository.findById(targetId);

        assertThat(updatedItem).isPresent();
        assertThat(updatedItem.get().getStatus()).isEqualTo(updateStatus);
    }

    @Test
    void updateItemInfo_전체수정_성공_테스트() {
        // given
        var targetId = 5L;
        var updateName = "updateItemName";
        var updatePrice = 30000;
        var updateQuantity = 1000;
        var updateRequest = new UpdateItemInfoCommand(updateName, updatePrice, updateQuantity);

        // when
        itemService.updateItemInfo(targetId, updateRequest);

        // then
        Optional<ItemEntity> updatedItem = itemEntityRepository.findById(targetId);
        assertThat(updatedItem).isPresent();
        assertThat(updatedItem.get().getItemName()).isEqualTo(updateName);
        assertThat(updatedItem.get().getItemPrice()).isEqualTo(updatePrice);
        assertThat(updatedItem.get().getStockQuantity()).isEqualTo(updateQuantity);
    }

    @Test
    void updateItemInfo_부분수정_성공_테스트() {
        // given
        var targetId = 2L;
        var targetIdx = 1;
        var updateName = "updateItemName";
        var updateQuantity = 1000;
        var updateRequest = new UpdateItemInfoCommand(updateName, null, updateQuantity);

        // when
        itemService.updateItemInfo(targetId, updateRequest);

        // then
        Optional<ItemEntity> updatedItem = itemEntityRepository.findById(targetId);
        assertThat(updatedItem).isPresent();
        assertThat(updatedItem.get().getItemName()).isEqualTo(updateName);
        assertThat(updatedItem.get().getItemPrice()).isEqualTo(items.get(targetIdx).getItemPrice());
        assertThat(updatedItem.get().getStockQuantity()).isEqualTo(updateQuantity);
    }

    @Test
    void delete_성공_테스트() {
        // given
        var deleteId = 1L;

        // when
        itemService.delete(deleteId);

        // then
        Optional<ItemEntity> deletedItem = itemEntityRepository.findById(deleteId);

        assertThat(deletedItem).isNotPresent();
    }

    @Test
    void getItem() {
        // given
        var targetId = 3L;

        // when
        ItemEntity findItem = itemService.getItem(targetId);

        // then
        assertThat(findItem.getId()).isEqualTo(targetId);
        assertThat(findItem.getItemName()).isEqualTo(items.get(2).getItemName());
        assertThat(findItem.getItemPrice()).isEqualTo(items.get(2).getItemPrice());
    }

    @Test
    void getItems() {
        // when
        List<ItemEntity> result = itemService.getItems();

        // then
        assertThat(result.size()).isEqualTo(items.size());
    }
}