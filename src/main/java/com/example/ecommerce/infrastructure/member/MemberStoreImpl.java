package com.example.ecommerce.infrastructure.member;

import com.example.ecommerce.member.MemberEntity;
import com.example.ecommerce.member.MemberStore;
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
