package com.example.ecommerce.infrastructure.member;

import com.example.ecommerce.common.exception.EntityNotFoundException;
import com.example.ecommerce.member.MemberEntity;
import com.example.ecommerce.member.MemberReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberReaderImpl implements MemberReader {

    private final MemberEntityRepository memberRepository;

    @Override
    public MemberEntity findById(Long memberId) {
        log.info("{}: {}, param value={}", getClass().getName(), "getById", memberId);
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("MemberEntity Not Found!"));
    }

    @Override
    public MemberEntity findByUsername(String username) {
        log.info("{}: {}, param value={}", getClass().getName(), "getByUsername", username);

        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("MemberEntity Not Found!"));
    }

    @Override
    public List<MemberEntity> findAll() {
        log.info("{}: {}", getClass().getName(), "findAll");
        return memberRepository.findAll();
    }
}
