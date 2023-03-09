package com.example.ecommerce.infrastructure.member;

import com.example.ecommerce.domain.member.MemberEntity;
import com.example.ecommerce.domain.member.MemberStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberStoreImpl implements MemberStore {
    private final MemberEntityRepository memberRepository;

    @Override
    public Long store(MemberEntity member) {
        return memberRepository.save(member).getId();
    }
}
