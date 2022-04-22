package com.soma.lightening.exception.error;

import com.soma.lightening.exception.ErrorCode;

public class OAuth2LoginFailedException  extends RuntimeException {
    public OAuth2LoginFailedException(){
        super(ErrorCode.OAUTH2_LOGIN_FAILED.getMessage());
    }

}
