package com.example.ecommerce.domain.member.membership;

public interface MembershipUpdateExecutor {

    void bulkUpdateGrade(int goe, MembershipEntity.Grade changeTo);

    void bulkUpdateGrade(int goe, int lt, MembershipEntity.Grade changeTo);

    void bulkResetTotalSpending();
}
