package com.example.ecommerce.domain.member.membership.persistence;

import com.example.ecommerce.domain.member.membership.entity.MembershipEntity;

public interface MembershipUpdateExecutor {

    void bulkUpdateGrade(int goe, MembershipEntity.Grade changeTo);

    void bulkUpdateGrade(int goe, int lt, MembershipEntity.Grade changeTo);

    void bulkResetTotalSpending();
}
