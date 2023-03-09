package com.example.ecommerce.domain.member.dto;

public record MemberUpdateInfoCommand(
        Long memberId,
        String email,
        String phoneNumber
) {}
