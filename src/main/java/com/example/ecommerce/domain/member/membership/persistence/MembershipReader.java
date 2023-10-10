package com.example.ecommerce.domain.member.membership.persistence;

import com.example.ecommerce.domain.member.membership.entity.MembershipEntity;

public interface MembershipReader {

    MembershipEntity findById(Long id);
}
