package com.example.ecommerce.interfaces.member.dto.request;

import javax.validation.constraints.NotNull;

public record MemberInfoUpdateRequestDto(
        @NotNull
        Long memberId,
        String email,
        String phoneNumber
) {}
