package com.example.ecommerce.domain.member.dto;

import com.example.ecommerce.domain.member.MemberEntity;

public record MemberDto(
        Long memberId,
        String username,
        String email,
        String phoneNumber,
        MemberEntity.Role role
) {
}
