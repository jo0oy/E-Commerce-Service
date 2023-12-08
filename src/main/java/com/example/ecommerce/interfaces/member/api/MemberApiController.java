package com.example.ecommerce.interfaces.member.api;

import com.example.ecommerce.application.member.MemberFacade;
import com.example.ecommerce.common.utils.ApiUtils;
import com.example.ecommerce.interfaces.member.dto.MemberDtoMapper;
import com.example.ecommerce.interfaces.member.dto.request.MemberInfoUpdateRequestDto;
import com.example.ecommerce.interfaces.member.dto.request.MemberJoinRequestDto;
import com.example.ecommerce.interfaces.member.dto.request.MemberPwdUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
@RestController
public class MemberApiController {

    private final MemberFacade memberFacade;
    private final MemberDtoMapper memberDtoMapper;

    @PostMapping("")
    public ResponseEntity<?> join(@Valid @RequestBody MemberJoinRequestDto requestDto) {
        var data = memberFacade.join(memberDtoMapper.of(requestDto));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiUtils.success(memberDtoMapper.of(data)));
    }

    @PutMapping("")
    public ResponseEntity<?> update(@Valid @RequestBody MemberInfoUpdateRequestDto requestDto) {

        memberFacade.updateInfo(requestDto.memberId(), memberDtoMapper.of(requestDto));

        return ResponseEntity.ok(ApiUtils.success("회원 정보를 정상적으로 업데이트 완료했습니다."));
    }

    @PutMapping("/{memberId}")
    public ResponseEntity<?> updatePassword(@Valid @RequestBody MemberPwdUpdateRequestDto requestDto,
                                            @PathVariable("memberId") Long memberId) {

        memberFacade.updatePassword(memberId, memberDtoMapper.of(requestDto));

        return ResponseEntity.ok(ApiUtils.success("비밀번호를 정상적으로 변경 완료했습니다."));
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<?> delete(@PathVariable("memberId") Long memberId) {
        memberFacade.delete(memberId);

        return ResponseEntity.ok(ApiUtils.success("정상적으로 회원을 삭제했습니다."));
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<?> getMember(@PathVariable("memberId") Long memberId) {
        var data = memberDtoMapper.of(memberFacade.getMember(memberId));

        return ResponseEntity.ok(ApiUtils.success(data));
    }
}
