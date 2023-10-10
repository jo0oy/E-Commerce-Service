package com.example.ecommerce.infrastructure.member.membership;

import com.example.ecommerce.common.exception.EntityNotFoundException;
import com.example.ecommerce.domain.member.membership.entity.MembershipEntity;
import com.example.ecommerce.domain.member.membership.persistence.MembershipReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MembershipReaderImpl implements MembershipReader {

    private final MembershipEntityRepository membershipRepository;

    @Override
    public MembershipEntity findById(Long id) {
        log.info("{}:::{}", getClass().getSimpleName(), "findById");
        return membershipRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("MembershipEntity Not Found!"));
    }
}
