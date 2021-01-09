package com.dursuneryilmaz.duscrumtool.model.request;

import javax.validation.constraints.Size;
import java.io.Serializable;

public class UserNewPasswordRequestModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String token;
    @Size(min = 8, max = 16, message = "Enter a password in length 8-16 character!")
    private String password;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}