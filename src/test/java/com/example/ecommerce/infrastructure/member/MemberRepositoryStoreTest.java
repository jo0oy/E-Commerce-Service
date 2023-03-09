package com.example.ecommerce.infrastructure.member;

import com.example.ecommerce.domain.member.MemberEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class MemberRepositoryStoreTest {

    @Autowired
    private MemberEntityRepository memberRepository;

    @Autowired
    private EntityManager em;

    @Test
    void member_entity_저장_성공_테스트() {
        // given
        var username = "member";
        var email = username + "@email.com";
        var password = username + "pw00!";
        var phoneNumber = "010-1111-1111";
        var memberEntity
                = MemberEntity.builder()
                .username(username)
                .email(email)
                .password(password)
                .phoneNumber(phoneNumber)
                .build();

        // when
        var savedMember = memberRepository.save(memberEntity);

        // then
        assertThat(savedMember.getUsername()).isEqualTo(username);
        assertThat(savedMember.getEmail()).isEqualTo(email);
        assertThat(savedMember.getRole()).isEqualTo(MemberEntity.Role.ROLE_USER);
    }

    @Test
    void member_entity_유니크_제약조건_테스트() {
        // given
        var username = "member";
        var email = username + "@email.com";
        var password = username + "pw00!";
        var phoneNumber = "010-1111-1111";
        var memberEntity1
                = MemberEntity.builder()
                .username(username)
                .email(email)
                .password(password)
                .phoneNumber(phoneNumber)
                .build();

        memberRepository.save(memberEntity1);
        em.flush();

        var memberEntity2
                = MemberEntity.builder()
                .username(username)
                .email(email)
                .password(password)
                .phoneNumber("010-1111-2222")
                .build();

        // when & then
        assertThatThrownBy(() -> memberRepository.save(memberEntity2))
                .isInstanceOf(DataIntegrityViolationException.class);
    }
}
