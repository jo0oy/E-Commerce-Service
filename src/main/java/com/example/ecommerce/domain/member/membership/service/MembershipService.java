package com.example.ecommerce.domain.member.membership.service;

import com.example.ecommerce.domain.member.membership.entity.MembershipEntity;
import com.example.ecommerce.domain.member.membership.persistence.MembershipUpdateExecutor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MembershipService {

    private final MembershipUpdateExecutor membershipUpdateExecutor;

    @Transactional
    public void updateMemberships() {

        var silver = MembershipEntity.Grade.SILVER;
        var gold = MembershipEntity.Grade.GOLD;
        var vip = MembershipEntity.Grade.VIP;

        // 각 등급별로 업데이트
        membershipUpdateExecutor.bulkUpdateGrade(silver.getGoe(), silver.getLt(), silver);
        membershipUpdateExecutor.bulkUpdateGrade(gold.getGoe(), gold.getLt(), gold);
        membershipUpdateExecutor.bulkUpdateGrade(vip.getGoe(), vip);

        // 총 지출액 초기화
        membershipUpdateExecutor.bulkResetTotalSpending();
    }
}
