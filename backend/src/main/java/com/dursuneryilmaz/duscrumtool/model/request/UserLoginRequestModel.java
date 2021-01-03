package com.dursuneryilmaz.duscrumtool.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class UserLoginRequestModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Email can not be blank!")
    @Email(message = "Enter a valid email address!")
    private String email;
    @NotBlank(message = "Password can not be blank!")
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
