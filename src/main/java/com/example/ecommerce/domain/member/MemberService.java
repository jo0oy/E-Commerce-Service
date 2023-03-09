package com.example.ecommerce.domain.member;

import com.example.ecommerce.common.exception.InvalidParamException;
import com.example.ecommerce.domain.member.dto.MemberJoinCommand;
import com.example.ecommerce.domain.member.dto.MemberDto;
import com.example.ecommerce.domain.member.dto.MemberUpdateInfoCommand;
import com.example.ecommerce.domain.member.dto.MemberUpdatePasswordCommand;
import com.example.ecommerce.domain.member.membership.MembershipEntity;
import com.example.ecommerce.domain.member.membership.MembershipStore;
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
    private final MembershipStore membershipStore;

    // 회원 가입
    @Transactional
    public Long join(MemberJoinCommand command) {
        log.info("{}: {}", getClass().getSimpleName(), "join");

        // 중복 아이디 조회
        validateDuplicateUsername(command.username());

        // 멤버십 초기 생성
        var membershipId = membershipStore.store(MembershipEntity.create());

        return memberStore.store(command.toEntity(membershipId));
    }

    // 회원 정보 수정
    @Transactional
    public void update(MemberUpdateInfoCommand command) {
        log.info("{}: {}", getClass().getSimpleName(), "update");

        var member = memberReader.findById(command.memberId());

        member.update(command.email(), command.phoneNumber());
    }

    // 회원 비밀번호 변경
    @Transactional
    public void updatePassword(MemberUpdatePasswordCommand command) {
        log.info("{}: {}", getClass().getSimpleName(), "updatePassword");

        var member = memberReader.findById(command.memberId());

        member.updatePassword(command.password());
    }

    // 회원 삭제 - soft delete
    @Transactional
    public void delete(Long memberId) {
        log.info("{}: {}", getClass().getSimpleName(), "delete");

        var member = memberReader.findById(memberId);

        member.delete();
    }

    // 회원 조회 by 회원 id
    public MemberDto getMember(Long memberId) {
        log.info("{}: {}", getClass().getSimpleName(), "getMember(Long)");

        return toDto(memberReader.findById(memberId));
    }

    // 회원 조회 by username
    public MemberDto getMember(String username) {
        log.info("{}: {}", getClass().getSimpleName(), "getMember(String)");

        return toDto(memberReader.findByUsername(username));
    }

    // 모든 회원 조회
    public List<MemberDto> getMembers() {
        log.info("{}: {}", getClass().getSimpleName(), "getMembers");

        return memberReader.findAll().stream()
                .map(this::toDto).toList();
    }

    // 중복 username 검증 메서드
    private void validateDuplicateUsername(String username) {
        if (memberReader.existsByUsername(username)) {
            log.error("이미 존재하는 username 입니다.");
            throw new InvalidParamException("duplicate username");
        }
    }

    // MemberDto 변환 메서드
    private MemberDto toDto(MemberEntity entity) {
        return new MemberDto(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPhoneNumber(),
                entity.getRole()
        );
    }
}
