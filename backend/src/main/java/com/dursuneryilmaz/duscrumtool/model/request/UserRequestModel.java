package com.dursuneryilmaz.duscrumtool.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class UserRequestModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "First name can not be blank!")
    private String firstName;
    @NotBlank(message = "Last name can not be blank!")
    private String lastName;
    @NotBlank(message = "Email can not be blank!")
    @Email(message = "Enter a valid email address!")
    private String email;

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
}
