package com.example.ecommerce.infrastructure.member;

import com.example.ecommerce.config.DatabaseCleanAfterEach;
import com.example.ecommerce.domain.member.MemberEntity;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DatabaseCleanAfterEach
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class MemberRepositoryReadTest {

    @Autowired
    private MemberEntityRepository memberRepository;

    @BeforeEach // auto_increment 로 동작하기 때문에 전체 테스트시 한번 생성된 데이터로 테스트하기 위해
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

    @DisplayName("[성공] 존재하는 회원을 id로 조회할 경우, 그에 해당하는 회원 엔티티를 반환")
    @Test
    void findById_성공_테스트() {
        //given
        var memberId = 1L;

        //when
        var findMember = memberRepository.findById(memberId);

        //then
        assertThat(findMember).isPresent();
        assertThat(findMember.get().getUsername()).isEqualTo("member1");
        assertThat(findMember.get().getEmail()).isEqualTo("member1@email.com");
    }

    @DisplayName("[실패] 존재하지 않는 회원 id로 조회할 경우, empty Optional 반환")
    @Test
    void findById_NotFound_테스트() {
        //given
        var memberId = 10L;

        //when
        var findMember = memberRepository.findById(memberId);

        // then
        assertThat(findMember).isNotPresent();
    }

    @DisplayName("[성공] 존재하는 회원 username 으로 조회할 경우, 그에 해당하는 회원 엔티티를 반환")
    @Test
    void findByUsername_성공_테스트() {
        //given
        var username = "member1";

        //when
        var findMember = memberRepository.findMemberEntityByUsername(username);

        //then
        assertThat(findMember).isPresent();
        assertThat(findMember.get().getUsername()).isEqualTo("member1");
        assertThat(findMember.get().getEmail()).isEqualTo("member1@email.com");
    }

    @DisplayName("[실패] 존재하지 않는 회원 username 으로 조회할 경우, empty Optional 반환")
    @Test
    void findByUsername_NotFound_테스트() {
        //given
        var username = "notFound";

        //when
        var findMember = memberRepository.findMemberEntityByUsername(username);

        // then
        assertThat(findMember).isNotPresent();
    }

    @DisplayName("[성공] 모든 회원 조회")
    @Test
    void findAll() {
        // when
        var result = memberRepository.findAll();

        // then
        assertThat(result).hasSize(5);
        assertThat(result.get(0).getId()).isEqualTo(1L);
    }
}
