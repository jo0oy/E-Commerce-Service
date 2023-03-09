package com.example.ecommerce.domain.member;

import com.example.ecommerce.common.exception.EntityNotFoundException;
import com.example.ecommerce.common.exception.InvalidParamException;
import com.example.ecommerce.config.DatabaseCleanAfterEach;
import com.example.ecommerce.domain.member.dto.MemberJoinCommand;
import com.example.ecommerce.domain.member.dto.MemberDto;
import com.example.ecommerce.domain.member.dto.MemberUpdateInfoCommand;
import com.example.ecommerce.domain.member.dto.MemberUpdatePasswordCommand;
import com.example.ecommerce.infrastructure.member.MemberEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DatabaseCleanAfterEach
@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberEntityRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        for (int i = 0; i < 5; i++) {
            var username = "member" + (i + 1);
            var email = username + "@email.com";
            var password = username + "pw00!";
            var phoneNumber = "010-0000-000" + (i + 1);

            memberRepository.save(
                    MemberEntity.builder()
                            .username(username)
                            .email(email)
                            .password(password)
                            .phoneNumber(phoneNumber)
                            .build()
            );
        }
    }

    @Test
    void join_성공_테스트() {

        //given
        var username = "newMember";
        var email = username + "@email.com";
        var password = username + "pw00!";
        var phoneNumber = "010-1111-1111";

        var memberCommand = new MemberJoinCommand(username, email, password, phoneNumber, null);

        //when
        var savedId = memberService.join(memberCommand);

        // then
        var savedMember = memberRepository.findMemberEntityById(savedId);

        assertThat(savedId).isGreaterThan(5L);
        assertThat(savedMember).isPresent();

        var member = savedMember.get();
        assertThat(member.getUsername()).isEqualTo(username);
        assertThat(member.getRole()).isEqualTo(MemberEntity.Role.ROLE_USER);
        assertThat(member.isDeleted()).isFalse();
    }

    @Test
    void join_중복_username_실패_테스트() {

        //given
        var username = "member1";
        var email = "newMember@email.com";
        var password = "newMemberpw00!";
        var phoneNumber = "010-1111-1111";

        var memberCommand = new MemberJoinCommand(username, email, password, phoneNumber, null);

        //when & then
        assertThatThrownBy(() -> memberService.join(memberCommand))
                .isInstanceOf(InvalidParamException.class)
                .hasMessageContaining("duplicate username");
    }

    @Test
    void update_이메일_전화번호_성공_테스트() {
        //given
        var memberId = 1L;
        var changeEmail = "mem1@email.com";
        var changeNum = "010-1212-1313";
        var command = new MemberUpdateInfoCommand(memberId, changeEmail, changeNum);

        //when
        memberService.update(command);

        //then
        var findMember = memberRepository.findMemberEntityById(memberId).get();

        assertThat(findMember.getEmail()).isEqualTo(changeEmail);
        assertThat(findMember.getPhoneNumber()).isEqualTo(changeNum);
    }

    @Test
    void update_부분_업데이트_성공_테스트() {
        //given
        var memberId = 1L;
        var changeEmail = "mem1@email.com";
        var command = new MemberUpdateInfoCommand(memberId, changeEmail, null);
        var before = memberRepository.findMemberEntityById(memberId).get();

        //when
        memberService.update(command);

        //then
        var findMember = memberRepository.findMemberEntityById(memberId).get();

        assertThat(findMember.getEmail()).isEqualTo(changeEmail);
        assertThat(findMember.getPhoneNumber()).isEqualTo(before.getPhoneNumber());
    }

    @Test
    void updatePassword_성공_테스트() {
        //given
        var memberId = 2L;
        var changePassword = "changePassword";
        var command = new MemberUpdatePasswordCommand(memberId, changePassword);

        //when
        memberService.updatePassword(command);

        //then
        var findMember = memberRepository.findMemberEntityById(memberId);

        assertThat(findMember).isPresent();
        assertThat(findMember.get().getPassword()).isEqualTo(changePassword);
    }

    @Test
    void delete_성공_테스트() {
        //given
        var memberId = 2L;

        //when
        memberService.delete(memberId);

        //then
        var findMember = memberRepository.findMemberEntityById(memberId);

        assertThat(findMember).isPresent();
        assertThat(findMember.get().isDeleted()).isTrue();
        assertThat(findMember.get().getDeletedAt()).isNotNull();
    }

    @Test
    void delete_EntityNotFound_실패_테스트() {
        //given
        var memberId = 10L;

        //when & then
        assertThatThrownBy(() -> memberService.delete(memberId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Entity Not Found");
    }

    @Test
    void getMember_성공_테스트() {
        //given
        var memberId = 1L;

        //when
        var result = memberService.getMember(memberId);

        //then
        assertThat(result).isInstanceOf(MemberDto.class);
        assertThat(result.memberId()).isEqualTo(memberId);
    }

    @Test
    void getMember_EntityNotFound_실패_테스트() {
        //given
        var memberId = 10L;

        //when & then
        assertThatThrownBy(() -> memberService.getMember(memberId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining("Entity Not Found");
    }

    @Test
    void getMembers_성공_테스트() {
        // when
        var result = memberService.getMembers();

        // then
        assertThat(result).hasSize(5);
    }
}
