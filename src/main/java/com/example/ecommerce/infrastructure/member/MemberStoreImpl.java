package com.example.ecommerce.infrastructure.member;

import com.example.ecommerce.domain.member.entity.MemberEntity;
import com.example.ecommerce.domain.member.persistence.MemberStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberStoreImpl implements MemberStore {
    private final MemberEntityRepository memberRepository;

    @Override
    public Long store(MemberEntity member) {
        log.info("{}:::{}", getClass().getSimpleName(), "store");
        return memberRepository.save(member).getId();
    }
}
