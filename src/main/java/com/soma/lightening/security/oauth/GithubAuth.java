package com.soma.lightening.security.oauth;

import lombok.Getter;

// 추상화해야함 나중에
@Getter
public class GithubAuth {
    private String clientId;
    private String secretId;

    public GithubAuth(String clientId, String secretId) {
        this.clientId = clientId;
        this.secretId = secretId;
    }
}
