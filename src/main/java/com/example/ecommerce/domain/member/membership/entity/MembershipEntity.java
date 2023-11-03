package com.example.ecommerce.domain.member.membership.entity;

import com.example.ecommerce.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "membership")
@Entity
public class MembershipEntity extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer totalSpending;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Getter
    public enum Grade {
        SILVER(0, 200000),
        GOLD(200000, 400000),
        VIP(400000);

        private int goe;
        private int lt;

        Grade(int goe) {
            this.goe = goe;
        }

        Grade(int goe, int lt) {
            this(goe);
            this.lt = lt;
        }
    }

    private boolean deleted;

    private LocalDateTime deletedAt;

    @Builder
    private MembershipEntity(Integer totalSpending,
                             Grade grade) {

        this.totalSpending = (Objects.isNull(totalSpending) || totalSpending < 0) ? 0 : totalSpending;
        this.grade = (Objects.isNull(grade)) ? Grade.SILVER : grade;
        this.deleted = false;
        this.deletedAt = null;
    }

    // 멤버십 초기 생성 메서드
    public static MembershipEntity create() {
        return MembershipEntity.builder()
                .totalSpending(0)
                .grade(Grade.SILVER)
                .build();
    }

    public void addTotalSpending(int spending) {
        this.totalSpending += spending;
    }

    public void minusTotalSpending(int spending) {
        this.totalSpending -= spending;
    }

    public void delete() {
        this.deleted = true;
        this.deletedAt = LocalDateTime.now();
    }
}
