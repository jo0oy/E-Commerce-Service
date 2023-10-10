package com.example.ecommerce.interfaces.member.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public record MemberPwdUpdateRequestDto(
        @NotBlank
        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
                message = "{Pattern.password}")
        String password
) {}
