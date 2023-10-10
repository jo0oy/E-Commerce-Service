package com.example.ecommerce.interfaces.member.dto;

import com.example.ecommerce.application.member.dto.MemberDto;
import com.example.ecommerce.domain.member.dto.MemberInfoUpdateCommand;
import com.example.ecommerce.domain.member.dto.MemberJoinCommand;
import com.example.ecommerce.domain.member.dto.MemberPwdUpdateCommand;
import com.example.ecommerce.application.member.dto.MembershipDto;
import com.example.ecommerce.interfaces.member.dto.request.MemberInfoUpdateRequestDto;
import com.example.ecommerce.interfaces.member.dto.request.MemberJoinRequestDto;
import com.example.ecommerce.interfaces.member.dto.request.MemberPwdUpdateRequestDto;
import com.example.ecommerce.interfaces.member.dto.response.MemberJoinResponseDto;
import com.example.ecommerce.interfaces.member.dto.response.MemberResponseDto;
import com.example.ecommerce.interfaces.member.dto.response.MembershipResponseDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface MemberDtoMapper {

    // to ResponseDto
    MemberResponseDto of(MemberDto memberDto);

    MembershipResponseDto of(MembershipDto membershipDto);

    MemberJoinResponseDto of(Long memberId);

    // to Command

    MemberJoinCommand of(MemberJoinRequestDto requestDto);

    MemberInfoUpdateCommand of(MemberInfoUpdateRequestDto requestDto);

    MemberPwdUpdateCommand of(MemberPwdUpdateRequestDto requestDto);

}
