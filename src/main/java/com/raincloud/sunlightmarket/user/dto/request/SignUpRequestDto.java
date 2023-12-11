package com.raincloud.sunlightmarket.user.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignUpRequestDto {

    @Pattern(regexp = "^[a-z0-9]{8,15}+$")
    private final String nickname;

    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}+$")
    private final String password;

    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$\n")
    private final String email;

    private final String intro;
    private boolean admin = false;
    private String adminToken = "";

    @Builder
    public SignUpRequestDto(final String nickname, final String password, final String email, final String intro) {
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.intro = intro;
    }
}
