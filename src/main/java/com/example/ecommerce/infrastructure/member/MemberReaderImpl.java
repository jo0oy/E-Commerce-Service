package com.example.ecommerce.infrastructure.member;

import com.example.ecommerce.common.exception.EntityNotFoundException;
import com.example.ecommerce.domain.member.entity.MemberEntity;
import com.example.ecommerce.domain.member.persistence.MemberReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberReaderImpl implements MemberReader {

    private final MemberEntityRepository memberRepository;

    @Override
    public MemberEntity findById(Long memberId) {
        log.info("{}:::{}, param value={}", getClass().getSimpleName(), "findById", memberId);
        return memberRepository.findMemberEntityById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("MemberEntity Not Found!"));
    }

    @Override
    public MemberEntity findByUsername(String username) {
        log.info("{}:::{}, param value={}", getClass().getName(), "findByUsername", username);

        return memberRepository.findMemberEntityByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("MemberEntity Not Found!"));
    }

    @Override
    public MemberEntity findByIdFetchMembership(Long memberId) {
        log.info("{}:::{}, param value={}", getClass().getSimpleName(), "findByIdFetchMembership", memberId);

        return memberRepository.findByIdFetchMembership(memberId)
                .orElseThrow(() -> new EntityNotFoundException("MemberEntity Not Found!"));
    }

    @Override
    public MemberEntity findByUsernameFetchMembership(String username) {
        log.info("{}:::{}, param value={}", getClass().getSimpleName(), "findByUsernameFetchMembership", username);

        return memberRepository.findByUsernameFetchMembership(username)
                .orElseThrow(() -> new EntityNotFoundException("MemberEntity Not Found!"));
    }

    @Override
    public boolean existsByUsername(String username) {
        log.info("{}:::{}, param value={}", getClass().getSimpleName(), "existsByUsername", username);

        return memberRepository.existsByUsername(username);
    }

    @Override
    public List<MemberEntity> findAll() {
        log.info("{}:::{}", getClass().getSimpleName(), "findAll");

        return memberRepository.findAll().stream()
                .filter(m -> !m.isDeleted()).collect(Collectors.toList());
    }

    @Override
    public Page<MemberEntity> findAll(Pageable pageable) {
        log.info("{}:::{}, size={}, offset={}, sort={}",
                getClass().getSimpleName(), "findAll", pageable.getPageSize(), pageable.getOffset(), pageable.getSort());

        return memberRepository.findAll(pageable);
    }
}
