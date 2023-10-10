package com.example.ecommerce.interfaces.member.dto.request;

import com.example.ecommerce.domain.member.entity.MemberEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public record MemberJoinRequestDto (
        @NotBlank
        @Size(min = 4, max = 25, message = "{Size.username}")
        String username,
        @NotBlank
        @Email(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
        String email,
        @NotBlank
        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
                message = "{Pattern.password}")
        String password,
        @NotBlank
        @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$",
                message = "{Pattern.phoneNumber}")
        String phoneNumber,
        MemberEntity.Role role
) {}
