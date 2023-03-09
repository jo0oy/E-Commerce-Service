package com.example.ecommerce.domain.member.dto;

import com.example.ecommerce.domain.member.MemberEntity;

public record MemberJoinCommand
        (String username,
         String email,
         String password,
         String phoneNumber,
         MemberEntity.Role role){

    public MemberEntity toEntity(Long membershipId) {
        return MemberEntity.builder()
                .username(username)
                .email(email)
                .password(password)
                .phoneNumber(phoneNumber)
                .membershipId(membershipId)
                .role(role)
                .build();
    }
}
