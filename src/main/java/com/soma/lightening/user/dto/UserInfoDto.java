package com.soma.lightening.user.dto;

import com.soma.lightening.common.entity.OAuth2Account;
import lombok.Builder;

@Builder
public class UserInfoDto {
    private String username;
    private String profileImage;
    private String nickname;

    public static UserInfoDto of(OAuth2Account account) {
        return UserInfoDto.builder()
                .username(account.getUsername())
                .profileImage(account.getProfileImage())
                .nickname(account.getNickname())
                .build();
    }
}
