package com.example.ecommerce.interfaces.member.dto.response;

import com.example.ecommerce.domain.member.entity.MemberEntity;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record MemberResponseDto(
        Long memberId,
        String username,
        String email,
        String phoneNumber,
        MemberEntity.Role role,
        MembershipResponseDto membership
) {}
