package com.example.ecommerce.domain.member.persistence;

import com.example.ecommerce.domain.member.entity.MemberEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MemberReader {

    MemberEntity findById(Long memberId);

    MemberEntity findByUsername(String username);

    MemberEntity findByIdFetchMembership(Long memberId);

    MemberEntity findByUsernameFetchMembership(String username);

    boolean existsByUsername(String username);

    List<MemberEntity> findAll();

    Page<MemberEntity> findAll(Pageable pageable);
}
