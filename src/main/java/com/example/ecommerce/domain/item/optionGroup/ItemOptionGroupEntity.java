package com.example.ecommerce.domain.item.optionGroup;

import com.example.ecommerce.common.exception.InvalidParamException;
import com.example.ecommerce.domain.BaseTimeEntity;
import com.example.ecommerce.domain.item.entity.ItemEntity;
import com.example.ecommerce.domain.item.option.ItemOptionEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "item_option_group")
@Entity
public class ItemOptionGroupEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemOptionGroupName;

    private Integer ordering;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private ItemEntity item;

    @OneToMany(mappedBy = "itemOptionGroup", cascade = CascadeType.ALL)
    private Set<ItemOptionEntity> itemOptions = new LinkedHashSet<>();

    @Builder
    private ItemOptionGroupEntity(String itemOptionGroupName,
                                  Integer ordering,
                                  ItemEntity item,
                                  Set<ItemOptionEntity> itemOptions) {

        if(!StringUtils.hasText(itemOptionGroupName)) throw new InvalidParamException("invalid itemOptionGroup.itemOptionGroupName");
        if(ObjectUtils.isEmpty(ordering)) throw new InvalidParamException("invalid itemOptionGroup.ordering");
        if(ObjectUtils.isEmpty(item)) throw new InvalidParamException("invalid itemOptionGroup.item");

        this.itemOptionGroupName = itemOptionGroupName;
        this.ordering = ordering;
        this.item = item;
        if(!ObjectUtils.isEmpty(itemOptions)) {
            this.itemOptions.addAll(itemOptions);
        }
    }
}
