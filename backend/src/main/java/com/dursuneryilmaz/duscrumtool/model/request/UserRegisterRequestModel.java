package com.dursuneryilmaz.duscrumtool.model.request;

import javax.validation.constraints.*;
import java.io.Serializable;

public class UserRegisterRequestModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "First name can not be blank!")
    private String firstName;
    @NotBlank(message = "Last name can not be blank!")
    private String lastName;
    @NotBlank(message = "Email can not be blank!")
    @Email(message = "Enter a valid email address!")
    private String email;
    @NotBlank(message = "Password can not be blank!")
    @Size(min = 8, max = 16, message = "Enter a password in length 8-16 character!")
    private String password;
    @NotBlank(message = "Confirm password can not be blank!")
    private String confirmPassword;

    @AssertTrue(message = "Confirm password does not equal to password")
    private boolean isValid() {
        return this.password.equals(this.confirmPassword);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
