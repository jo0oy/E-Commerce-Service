package com.example.ecommerce.application.member.dto;

import com.example.ecommerce.domain.member.membership.entity.MembershipEntity;

public record MembershipDto(
        Long membershipId,
        Integer totalSpending,
        MembershipEntity.Grade grade
) {}
