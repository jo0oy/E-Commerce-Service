package com.example.ecommerce.domain.item.entity;

import com.example.ecommerce.common.exception.InvalidParamException;
import com.example.ecommerce.domain.BaseTimeEntity;
import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "item")
@Entity
public class ItemEntity extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private Integer itemPrice;

    @Column(nullable = false)
    private Integer stockQuantity;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Getter
    @RequiredArgsConstructor
    public enum Status {
        PREPARE("판매준비중"),
        ON_SALES("판매중"),
        END_OF_SALES("판매종료");

        private final String description;


    }
    private boolean deleted;
    private LocalDateTime deletedAt;

    @Builder
    private ItemEntity(String itemName,
                       Integer itemPrice,
                       Integer stockQuantity,
                       Status status) {

        if (!StringUtils.hasText(itemName))
            throw new InvalidParamException("empty item.itemName");
        if (Objects.isNull(itemPrice))
            throw new InvalidParamException("invalid item.itemPrice");
        if (Objects.isNull(stockQuantity))
            throw new InvalidParamException("invalid item.stockQuantity");

        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.stockQuantity = stockQuantity;
        this.status = Objects.isNull(status) ? Status.PREPARE : status;
        this.deleted = false;
        this.deletedAt = null;
    }

    public void changeStatus(Status status) {
        if(Objects.isNull(status)) throw new InvalidParamException("Item.status must not null");
        this.status = status;
    }

    public void update(String itemName, Integer itemPrice, Integer stockQuantity) {
        if(StringUtils.hasText(itemName)) this.itemName = itemName;
        if(Objects.nonNull(itemPrice) && itemPrice >= 500) this.itemPrice = itemPrice;
        if(Objects.nonNull(stockQuantity) && stockQuantity >= 10) this.stockQuantity = stockQuantity;
    }

    public void delete() {
        this.deleted = true;
        this.deletedAt = LocalDateTime.now();
    }
}
