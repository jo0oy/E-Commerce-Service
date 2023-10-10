package com.example.ecommerce.domain.member.dto;

import com.example.ecommerce.domain.member.entity.MemberEntity;
import com.example.ecommerce.domain.member.membership.entity.MembershipEntity;

public record MemberJoinCommand
        (String username,
         String email,
         String password,
         String phoneNumber,
         MemberEntity.Role role){

    public MemberEntity toEntity(MembershipEntity membership) {
        return MemberEntity.builder()
                .username(username)
                .email(email)
                .password(password)
                .phoneNumber(phoneNumber)
                .membership(membership)
                .role(role)
                .build();
    }
}
