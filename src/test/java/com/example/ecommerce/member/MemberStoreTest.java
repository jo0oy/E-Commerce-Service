package com.example.ecommerce.member;

import com.example.ecommerce.config.DatabaseCleanAfterEach;
import com.example.ecommerce.infrastructure.member.MemberEntityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@DatabaseCleanAfterEach
@SpringBootTest
public class MemberStoreTest {

    @Autowired
    private MemberEntityRepository memberRepository;

    @Autowired
    private MemberStore memberStore;

    @Test
    void store_성공_테스트() {
        //given
        var memberEntity
                = MemberEntity.builder()
                .username("member")
                .email("member@email.com")
                .phoneNumber("010-1111-1111")
                .password("member1pw")
                .build();

        //when
        var savedId = memberStore.store(memberEntity);

        //then
        assertThat(savedId).isNotNull();
        assertThat(savedId).isGreaterThan(0L);
    }
}
