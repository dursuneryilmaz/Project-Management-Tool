package com.dursuneryilmaz.duscrumtool.shared.constant;

public class GmailConstants {
    // common
    public static final String FROM = "akdenizsilver@gmail.com";

    // email verification
    public static final String EMAIL_VERIFICATION_SUBJECT = "DuScrumTool Email Verification.";

    public static final String EMAIL_VERIFICATION_URL = "http://localhost:8888/duscrumtool/api/v1/users/email-verification?token=$tokenValue";

    public static final String EMAIL_VERIFICATION_HTML_BODY = "<h1>Please Verify Your Account</h1>"
            + "<p>Thank you for your registration. Please confirm your email address by clicking"
            + "<a href='" + EMAIL_VERIFICATION_URL + "'>Here</a>"
            + "</p>";

    public static final String EMAIL_VERIFICATION_TEXT_BODY = "Please Verify Your Account"
            + "Thank you for your registration. Please confirm your email address by clicking following link"
            + EMAIL_VERIFICATION_URL;

    // password rest
    public static final String PASSWORD_RESET_SUBJECT = "DuScrumTool Password Reset";

    public static final String PASSWORD_RESET_URL = "http://localhost:8888/duscrumtool/api/v1/users/password-reset?token=$tokenValue";

    public static final String PASSWORD_RESET_HTML_BODY = "<h1>Password Reset Request</h1>"
            + "<p>Hi, $firstName !</p> "
            + "<p>Someone has requested to reset your password. If it were not you, please ignore it."
            + " otherwise please click"
            + "<a href='" + PASSWORD_RESET_URL + "'>"
            + " Here "
            + "</a><br/><br/>"
            + "Thank you!";

    public static final String PASSWORD_RESET_TEX_TBODY = "Password Reset Request "
            + "Hi, $firstName! "
            + "Someone has requested to reset your password. If it were not you, please ignore it."
            + " otherwise please open the link below in your browser window to set a new password:"
            + PASSWORD_RESET_URL
            + " Thank you!";
}
