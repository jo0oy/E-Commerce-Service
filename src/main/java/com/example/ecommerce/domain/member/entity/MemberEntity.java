package com.example.ecommerce.domain.member.entity;

import com.example.ecommerce.common.exception.InvalidParamException;
import com.example.ecommerce.domain.BaseTimeEntity;
import com.example.ecommerce.domain.member.membership.entity.MembershipEntity;
import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "members",
        indexes = @Index(name = "idx_username", columnList = "username", unique = true))
@Entity
public class MemberEntity extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 50, nullable = false, updatable = false)
    private String username;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 60, nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(updatable = false)
    private Role role;

    private boolean isDeleted;

    private LocalDateTime deletedAt;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "membership_id")
    private MembershipEntity membership;

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
                         MembershipEntity membership,
                         Role role) {

        if (!StringUtils.hasText(username)) throw new InvalidParamException("empty username");
        if (!StringUtils.hasText(email)) throw new InvalidParamException("empty email");
        if (!StringUtils.hasText(password)) throw new InvalidParamException("empty password");
        if (!StringUtils.hasText(phoneNumber)) throw new InvalidParamException("empty phoneNumber");

        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.membership = (Objects.isNull(membership)) ? MembershipEntity.create() : membership;
        this.role = (Objects.isNull(role)) ? Role.ROLE_USER : role;
        this.isDeleted = false;
        this.deletedAt = null;
    }

    public void update(String email, String phoneNumber) {
        if(StringUtils.hasText(email)) this.email = email;
        if(StringUtils.hasText(phoneNumber)) this.phoneNumber = phoneNumber;
    }

    public void updatePassword(String password) {
        if(StringUtils.hasText(password)) this.password = password;
    }

    public void delete() {
        this.isDeleted = true;
        this.deletedAt = LocalDateTime.now();
    }
}
