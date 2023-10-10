package com.example.ecommerce.application.member;

import com.example.ecommerce.domain.member.service.MemberService;
import com.example.ecommerce.application.member.dto.MemberDto;
import com.example.ecommerce.domain.member.dto.MemberJoinCommand;
import com.example.ecommerce.domain.member.dto.MemberInfoUpdateCommand;
import com.example.ecommerce.domain.member.dto.MemberPwdUpdateCommand;
import com.example.ecommerce.domain.member.entity.MemberEntity;
import com.example.ecommerce.domain.member.membership.entity.MembershipEntity;
import com.example.ecommerce.application.member.dto.MembershipDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberFacade {

    // 생성, 업데이트, 삭제
    private final MemberService memberService;

    public Long join(MemberJoinCommand command) {
        return memberService.join(command);
    }

    public void updateInfo(Long memberId, MemberInfoUpdateCommand command) {
        memberService.update(memberId, command);
    }

    public void updatePassword(Long memberId, MemberPwdUpdateCommand command) {
        memberService.updatePassword(memberId, command);
    }

    public void delete(Long memberId) {
        memberService.delete(memberId);
    }

    // 조회
    public MemberDto getMember(Long memberId) {
        return toDto(memberService.getMember(memberId));
    }

    public MemberDto getMember(String username) {
        return toDto(memberService.getMember(username));
    }

    public List<MemberDto> getMembers() {
        return memberService.getMembers()
                .stream()
                .map(this::toDto)
                .toList();
    }

    private MemberDto toDto(MemberEntity entity) {
        return new MemberDto(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPhoneNumber(),
                entity.getRole(),
                toDto(entity.getMembership())
        );
    }

    private MembershipDto toDto(MembershipEntity entity) {
        return new MembershipDto(
                entity.getId(),
                entity.getTotalSpending(),
                entity.getGrade()
        );
    }
}
