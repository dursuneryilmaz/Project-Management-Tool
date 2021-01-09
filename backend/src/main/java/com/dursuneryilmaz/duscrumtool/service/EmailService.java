package com.dursuneryilmaz.duscrumtool.service;

import com.dursuneryilmaz.duscrumtool.domain.User;

public interface EmailService {
    boolean sendVerificationEmail(User user);

    boolean sendPasswordResetMail(String email, String firstName, String token);
}
