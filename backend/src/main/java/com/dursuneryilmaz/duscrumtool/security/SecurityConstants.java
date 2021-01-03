package com.dursuneryilmaz.duscrumtool.security;

public class SecurityConstants {
    // these can be fetched from application properties or yml

    // times
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final long PASSWORD_RESET_TOKEN_EXPIRATION_TIME = 3_600_000; // 1hour
    // token properties
    public static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_SECRET = "tokenSecret";
    // URIs
    public static final String REGISTER_URI = "/api/v1/users/register";
    public static final String LOGIN_URI = "/api/v1/users/login";
    public static final String EMAIL_VERIFICATION_URI = "/api/v1/users/email-verification";
    public static final String PASSWORD_RESET_REQUEST_URI = "/api/v1/users/password-reset-request";
    public static final String PASSWORD_RESET_URI = "/api/v1//users/password-reset";
    public static final String H2_CONSOLE_URI = "h2-console/**";
}
