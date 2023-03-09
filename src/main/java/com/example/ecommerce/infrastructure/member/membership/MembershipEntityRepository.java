package com.example.ecommerce.infrastructure.member.membership;

import com.example.ecommerce.domain.member.membership.MembershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MembershipEntityRepository extends JpaRepository<MembershipEntity, Long> {

    @Modifying(clearAutomatically = true)
    @Query("update MembershipEntity m set m.grade = :changeTo where m.totalSpending >= :goe and m.isDeleted = false")
    void bulkUpdateGrade(@Param("goe") int goe, @Param("changeTo") MembershipEntity.Grade changeTo);

    @Modifying(clearAutomatically = true)
    @Query("update MembershipEntity m set m.grade = :changeTo " +
            "where m.totalSpending >= :goe and m.totalSpending < :lt and m.isDeleted = false")
    void bulkUpdateGrade(@Param("goe") int goe, @Param("lt") int lt, @Param("changeTo") MembershipEntity.Grade changeTo);

    @Modifying(clearAutomatically = true)
    @Query("update MembershipEntity m set m.totalSpending = 0 where m.isDeleted = false")
    void bulkResetTotalSpending();
}
