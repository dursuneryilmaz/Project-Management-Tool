package com.dursuneryilmaz.duscrumtool.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class UserResetPasswordRequestModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Email can not be blank!")
    @Email(message = "Enter a valid email address!")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
