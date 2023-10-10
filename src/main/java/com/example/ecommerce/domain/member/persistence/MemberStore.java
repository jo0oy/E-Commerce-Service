package com.example.ecommerce.domain.member.persistence;

import com.example.ecommerce.domain.member.entity.MemberEntity;

public interface MemberStore {
    Long store(MemberEntity member);
}
