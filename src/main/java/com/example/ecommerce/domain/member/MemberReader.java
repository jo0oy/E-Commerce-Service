package com.example.ecommerce.domain.member;

import java.util.List;

public interface MemberReader {

    MemberEntity findById(Long memberId);

    MemberEntity findByUsername(String username);

    boolean existsByUsername(String username);

    List<MemberEntity> findAll();
}
