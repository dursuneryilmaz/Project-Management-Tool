package com.dursuneryilmaz.duscrumtool.service.impl;

import com.dursuneryilmaz.duscrumtool.domain.User;
import com.dursuneryilmaz.duscrumtool.service.EmailService;
import com.dursuneryilmaz.duscrumtool.shared.constant.GmailConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;


@Service
public class GmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public boolean sendVerificationEmail(User user) {
        String htmlBodyWithToken = GmailConstants.EMAIL_VERIFICATION_HTML_BODY.replace("$tokenValue", user.getEmailVerificationToken());

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setSubject(GmailConstants.EMAIL_VERIFICATION_SUBJECT);
            // get email address from application yml or spring email
            mimeMessageHelper.setFrom(new InternetAddress(GmailConstants.FROM));
            mimeMessageHelper.setTo(user.getEmail());
            mimeMessageHelper.setText(htmlBodyWithToken, true);


            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean sendPasswordResetMail(String email, String firstName, String token) {
        String htmlBodyWithToken = GmailConstants.PASSWORD_RESET_HTML_BODY.replace("$tokenValue", token);
        String htmlBodyWithTokenAndUserName = htmlBodyWithToken.replace("$firstName", firstName);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setSubject(GmailConstants.PASSWORD_RESET_SUBJECT);
            // get email address from application yml or spring email
            mimeMessageHelper.setFrom(new InternetAddress(GmailConstants.FROM));
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setText(htmlBodyWithTokenAndUserName, true);

            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
