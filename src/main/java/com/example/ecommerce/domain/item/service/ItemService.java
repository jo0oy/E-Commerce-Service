package com.example.ecommerce.domain.item.service;

import com.example.ecommerce.domain.item.dto.ChangeItemStatusCommand;
import com.example.ecommerce.domain.item.dto.RegisterItemCommand;
import com.example.ecommerce.domain.item.dto.UpdateItemInfoCommand;
import com.example.ecommerce.domain.item.entity.ItemEntity;
import com.example.ecommerce.domain.item.persistence.ItemReader;
import com.example.ecommerce.domain.item.persistence.ItemStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ItemService {

    private final ItemStore itemStore;
    private final ItemReader itemReader;

    @Transactional
    public Long registerItem(RegisterItemCommand command) {
        log.info("{}:::{}", getClass().getSimpleName(), "registerItem");

        return itemStore.store(command.toEntity());
    }

    @Transactional
    public void changeStatus(Long itemId, ChangeItemStatusCommand command) {
        log.info("{}:::{}", getClass().getSimpleName(), "changeStatus");

        var item = itemReader.findById(itemId);

        item.changeStatus(command.status());
    }

    @Transactional
    public void updateItemInfo(Long itemId, UpdateItemInfoCommand command) {
        log.info("{}:::{}", getClass().getSimpleName(), "updateItemInfo");

        var item = itemReader.findById(itemId);

        item.update(command.itemName(), command.itemPrice(), command.stockQuantity());
    }

    @Transactional
    public void delete(Long itemId) {
        log.info("{}:::{}", getClass().getSimpleName(), "delete");

        var item = itemReader.findById(itemId);

        item.delete();
    }

    public ItemEntity getItem(Long itemId) {
        log.info("{}:::{}", getClass().getSimpleName(), "getItem(Long)");

        return itemReader.findById(itemId);
    }

    public List<ItemEntity> getItems() {
        log.info("{}:::{}", getClass().getSimpleName(), "getItems");
        return itemReader.findAll();
    }
}
