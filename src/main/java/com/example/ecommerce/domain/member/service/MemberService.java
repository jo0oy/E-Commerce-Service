package com.example.ecommerce.domain.member.service;

import com.example.ecommerce.common.exception.InvalidParamException;
import com.example.ecommerce.domain.member.dto.MemberInfoUpdateCommand;
import com.example.ecommerce.domain.member.dto.MemberJoinCommand;
import com.example.ecommerce.domain.member.dto.MemberPwdUpdateCommand;
import com.example.ecommerce.domain.member.entity.MemberEntity;
import com.example.ecommerce.domain.member.membership.entity.MembershipEntity;
import com.example.ecommerce.domain.member.persistence.MemberReader;
import com.example.ecommerce.domain.member.persistence.MemberStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberReader memberReader;
    private final MemberStore memberStore;

    // 회원 가입
    @Transactional
    public Long join(MemberJoinCommand command) {
        log.info("{}:::{}", getClass().getSimpleName(), "join");

        // 중복 아이디 조회
        validateDuplicateUsername(command.username());

        // 멤버십 초기 생성
        var membership = MembershipEntity.create();

        return memberStore.store(command.toEntity(membership));
    }

    // 회원 정보 수정
    @Transactional
    public void update(Long memberId, MemberInfoUpdateCommand command) {
        log.info("{}:::{}", getClass().getSimpleName(), "update");

        var member = memberReader.findById(memberId);

        member.update(command.email(), command.phoneNumber());
    }

    // 회원 비밀번호 변경
    @Transactional
    public void updatePassword(Long memberId, MemberPwdUpdateCommand command) {
        log.info("{}:::{}", getClass().getSimpleName(), "updatePassword");

        var member = memberReader.findById(memberId);

        member.updatePassword(command.password());
    }

    // 회원 삭제 - soft delete
    @Transactional
    public void delete(Long memberId) {
        log.info("{}:::{}", getClass().getSimpleName(), "delete");

        // 회원 엔티티 조회
        var member = memberReader.findByIdFetchMembership(memberId);
        var membership = member.getMembership();

        // 삭제 로직 실행
        membership.delete();
        member.delete();
    }

    // 회원 조회 by 회원 id
    public MemberEntity getMember(Long memberId) {
        log.info("{}:::{}", getClass().getSimpleName(), "getMember(Long)");

        return memberReader.findByIdFetchMembership(memberId);
    }

    // 회원 조회 by username
    public MemberEntity getMember(String username) {
        log.info("{}:::{}", getClass().getSimpleName(), "getMember(String)");

        return memberReader.findByUsernameFetchMembership(username);
    }

    // 모든 회원 조회
    public List<MemberEntity> getMembers() {
        log.info("{}:::{}", getClass().getSimpleName(), "getMembers");

        return memberReader.findAll();
    }

    // 중복 username 검증 메서드
    private void validateDuplicateUsername(String username) {
        log.info("{}:::{}", getClass().getSimpleName(), "validateDuplicateUsername");
        if (memberReader.existsByUsername(username)) {
            log.error("이미 존재하는 username 입니다.");
            throw new InvalidParamException("duplicate username");
        }
    }
}
