package com.example.ecommerce.domain.item.option;

import com.example.ecommerce.domain.BaseTimeEntity;
import com.example.ecommerce.domain.item.optionGroup.ItemOptionGroupEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "item_option")
@Entity
public class ItemOptionEntity extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String itemOptionName;

    private Integer ordering;

    private Long itemOptionPrice;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "item_option_group_id")
    private ItemOptionGroupEntity itemOptionGroup;

    @Builder
    private ItemOptionEntity(String itemOptionName,
                             Integer ordering,
                             Long itemOptionPrice,
                             ItemOptionGroupEntity itemOptionGroup) {

        this.itemOptionName = itemOptionName;
        this.ordering = ordering;
        this.itemOptionPrice = (ObjectUtils.isEmpty(itemOptionPrice)) ? 0 : itemOptionPrice;
        this.itemOptionGroup = itemOptionGroup;
    }
}
