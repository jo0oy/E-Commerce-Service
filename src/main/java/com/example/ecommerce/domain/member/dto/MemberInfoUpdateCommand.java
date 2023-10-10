package com.example.ecommerce.domain.member.dto;

public record MemberInfoUpdateCommand(
        String email,
        String phoneNumber
) {}
