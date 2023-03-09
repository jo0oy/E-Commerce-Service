package com.example.ecommerce.domain.member.membership;

import com.example.ecommerce.config.DatabaseCleanAfterEach;
import com.example.ecommerce.infrastructure.member.membership.MembershipEntityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

@Sql("classpath:data/membership-test.sql")
@DatabaseCleanAfterEach
@SpringBootTest
class MembershipServiceTest {

    @Autowired
    private MembershipEntityRepository membershipRepository;

    @Autowired
    private MembershipService membershipService;

    @Test
    void updateMemberships_성공_테스트() {
        // when
        membershipService.updateMemberships();

        //then
        var result = membershipRepository.findAll();
        assertThat(result.get(5).getTotalSpending()).isEqualTo(0);

        var map = result.stream()
                .collect(Collectors.groupingBy(MembershipEntity::getGrade, Collectors.counting()));

        assertThat(map.get(MembershipEntity.Grade.SILVER)).isEqualTo(8);
        assertThat(map.get(MembershipEntity.Grade.GOLD)).isEqualTo(5);
        assertThat(map.get(MembershipEntity.Grade.VIP)).isEqualTo(7);
    }
}
