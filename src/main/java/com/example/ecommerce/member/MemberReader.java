package com.example.ecommerce.member;

import java.util.List;

public interface MemberReader {

    MemberEntity findById(Long memberId);

    MemberEntity findByUsername(String username);

    List<MemberEntity> findAll();
}
