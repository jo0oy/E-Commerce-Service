package com.example.ecommerce.member;

import org.springframework.stereotype.Component;

@Component
public interface MemberStore {
    Long store(MemberEntity member);
}
