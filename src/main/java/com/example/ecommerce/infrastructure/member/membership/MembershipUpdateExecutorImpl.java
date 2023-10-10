package com.example.ecommerce.infrastructure.member.membership;

import com.example.ecommerce.domain.member.membership.entity.MembershipEntity;
import com.example.ecommerce.domain.member.membership.persistence.MembershipUpdateExecutor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MembershipUpdateExecutorImpl implements MembershipUpdateExecutor {

    private final MembershipEntityRepository membershipRepository;

    @Override
    public void bulkUpdateGrade(int goe, MembershipEntity.Grade changeTo) {
        membershipRepository.bulkUpdateGrade(goe, changeTo);
    }

    @Override
    public void bulkUpdateGrade(int goe, int lt, MembershipEntity.Grade changeTo) {
        membershipRepository.bulkUpdateGrade(goe, lt, changeTo);
    }

    @Override
    public void bulkResetTotalSpending() {
        membershipRepository.bulkResetTotalSpending();
    }
}
