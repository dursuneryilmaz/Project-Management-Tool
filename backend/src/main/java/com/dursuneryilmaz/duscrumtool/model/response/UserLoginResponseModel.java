package com.dursuneryilmaz.duscrumtool.model.response;

import java.io.Serializable;

public class UserLoginResponseModel extends UserResponseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String jwtToken;
    private boolean isLoginSuccess;

    public UserLoginResponseModel(String jwtToken, boolean isLoginSuccess) {
        this.jwtToken = jwtToken;
        this.isLoginSuccess = isLoginSuccess;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public boolean isLoginSuccess() {
        return isLoginSuccess;
    }

    public void setLoginSuccess(boolean loginSuccess) {
        isLoginSuccess = loginSuccess;
    }
}
