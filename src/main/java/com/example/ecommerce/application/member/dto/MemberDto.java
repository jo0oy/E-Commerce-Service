package com.example.ecommerce.application.member.dto;

import com.example.ecommerce.domain.member.entity.MemberEntity;

public record MemberDto(
        Long memberId,
        String username,
        String email,
        String phoneNumber,
        MemberEntity.Role role,
        MembershipDto membership
) {
}
