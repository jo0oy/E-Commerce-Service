package com.example.ecommerce.interfaces.member.dto.response;

import com.example.ecommerce.domain.member.membership.entity.MembershipEntity;

public record MembershipResponseDto(
        Long membershipId,
        Integer totalSpending,
        MembershipEntity.Grade grade
) {}
