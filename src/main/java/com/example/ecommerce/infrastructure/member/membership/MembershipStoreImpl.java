package com.example.ecommerce.infrastructure.member.membership;

import com.example.ecommerce.domain.member.membership.MembershipEntity;
import com.example.ecommerce.domain.member.membership.MembershipStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MembershipStoreImpl implements MembershipStore {

    private final MembershipEntityRepository membershipRepository;

    @Override
    public Long store(MembershipEntity entity) {
        return membershipRepository.save(entity).getId();
    }
}
