package com.example.ecommerce.member;

import com.example.ecommerce.common.exception.InvalidParamException;
import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "members")
@Entity
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 50, nullable = false, updatable = false)
    private String username;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 60, nullable = false)
    private String phoneNumber;

    private Long membershipId;

    private Role role;

    @RequiredArgsConstructor
    @Getter
    public enum Role {
        ROLE_USER("회원"),
        ROLE_PARTNER("파트너사"),
        ROLE_ADMIN("관리자");

        private final String description;
    }
    @Builder
    private MemberEntity(String username,
                         String email,
                         String password,
                         String phoneNumber,
                         Long membershipId,
                         Role role) {

        if (!StringUtils.hasText(username)) throw new InvalidParamException("empty username");
        if (!StringUtils.hasText(email)) throw new InvalidParamException("empty email");
        if (!StringUtils.hasText(password)) throw new InvalidParamException("empty password");
        if (!StringUtils.hasText(phoneNumber)) throw new InvalidParamException("empty phoneNumber");

        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.membershipId = membershipId;
        this.role = (Objects.isNull(role)) ? Role.ROLE_USER : role;
    }

    public void update(String email, String phoneNumber) {
        if(StringUtils.hasText(email)) this.email = email;
        if(StringUtils.hasText(phoneNumber)) this.phoneNumber = phoneNumber;
    }

    public void updatePassword(String password) {
        if(StringUtils.hasText(password)) this.password = password;
    }
}
