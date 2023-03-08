package com.example.ecommerce.member;

import com.example.ecommerce.common.exception.EntityNotFoundException;
import com.example.ecommerce.config.DatabaseCleanAfterEach;
import com.example.ecommerce.infrastructure.member.MemberEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@DatabaseCleanAfterEach
@SpringBootTest
class MemberReaderTest {

    @Autowired
    private MemberEntityRepository memberRepository;

    @Autowired
    private MemberReader memberReader;

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
    void findById_성공_테스트() {
        //given
        var memberId = 1L;

        //when
        var findMember = memberReader.findById(memberId);

        //then
        assertThat(findMember).isNotNull();
        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    void findById_EntityNotFound_테스트() {
        //given
        var memberId = 10L;

        //when & then
        assertThatThrownBy(() -> memberReader.findById(memberId))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void findByUsername_성공_테스트() {
        //given
        var username = "member1";

        //when
        var findMember = memberReader.findByUsername(username);

        //then
        assertThat(findMember).isNotNull();
        assertThat(findMember.getEmail()).isEqualTo("member1@email.com");
    }

    @Test
    void findByUsername_EntityNotFound_테스트() {
        //given
        var username = "notFound";

        //when & then
        assertThatThrownBy(() -> memberReader.findByUsername(username))
                .isInstanceOf(EntityNotFoundException.class);
    }

    @Test
    void findAll() {
        //given

        //when
        var result = memberReader.findAll();

        //then
        assertThat(result).hasSize(5);
    }
}
