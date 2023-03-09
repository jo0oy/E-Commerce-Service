package com.example.ecommerce.domain.member;

import org.springframework.stereotype.Component;

@Component
public interface MemberStore {
    Long store(MemberEntity member);
}
