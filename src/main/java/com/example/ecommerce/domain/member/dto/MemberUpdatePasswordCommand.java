package com.example.ecommerce.domain.member.dto;

public record MemberUpdatePasswordCommand(Long memberId, String password) {}
