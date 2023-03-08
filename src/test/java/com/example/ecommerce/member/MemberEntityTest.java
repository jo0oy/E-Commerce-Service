package com.example.ecommerce.member;

import com.example.ecommerce.common.exception.InvalidParamException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MemberEntityTest {

    @Test
    void member_entity_입력_데이터_오류_테스트() {
        // given
        var username = "member";
        var password = username + "pw00!";
        var phoneNumber = "010-1111-1111";

        // when
        assertThatThrownBy(() -> MemberEntity.builder()
                .username(username)
                .email("")
                .password(password)
                .phoneNumber(phoneNumber)
                .build()
        ).isInstanceOf(InvalidParamException.class).hasMessage("empty email");
    }
}
