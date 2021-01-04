package com.dursuneryilmaz.duscrumtool.security.jwt;

import com.dursuneryilmaz.duscrumtool.domain.User;
import com.dursuneryilmaz.duscrumtool.security.SecurityConstants;
import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {
    // generate auth token
    public String generateAuthToken(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Date now = new Date(System.currentTimeMillis());

        Date expiryDate = new Date(now.getTime() + SecurityConstants.AUTH_TOKEN_EXPIRATION_TIME);

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getUserId());
        claims.put("username", user.getUsername());
        claims.put("firstName", user.getFirstName());
        claims.put("lastName", user.getLastName());
        claims.put("roles", user.getAuthorities());

        return Jwts.builder()
                .setSubject(user.getUserId())
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.AUTH_TOKEN_SECRET)
                .compact();
    }

    // generate email verification token
    public String generateEmailVerificationToken(String userId) {
        String token = Jwts.builder()
                .setSubject(userId)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EMAIL_VERIFICATION_TOKEN_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.EMAIL_VERIFICATION_TOKEN_SECRET)
                .compact();
        return token;
    }

    // generate password reset token
    public String generatePasswordResetToken(String userId) {
        String token = Jwts.builder()
                .setSubject(userId)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.PASSWORD_RESET_TOKEN_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.PASSWORD_RESET_TOKEN_SECRET)
                .compact();
        return token;
    }

    // validate auth token
    public boolean hasAuthTokenExpired(String token) {
        boolean hasTokenExpired = false;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SecurityConstants.AUTH_TOKEN_SECRET)
                    .parseClaimsJws(token).getBody();
            Date tokeExpirationDate = claims.getExpiration();
            Date todayDate = new Date();
            hasTokenExpired = tokeExpirationDate.before(todayDate);
        } catch (ExpiredJwtException e) {
            hasTokenExpired = true;
        } catch (SignatureException ex) {
            System.out.println("Invalid JWT Signature");
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT Token");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty");
        }
        return hasTokenExpired;

    }

    // validate email verification token
    public boolean hasEmailVerificationTokenExpired(String token) {
        boolean hasTokenExpired = false;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SecurityConstants.EMAIL_VERIFICATION_TOKEN_SECRET)
                    .parseClaimsJws(token).getBody();
            Date tokeExpirationDate = claims.getExpiration();
            Date todayDate = new Date();
            hasTokenExpired = tokeExpirationDate.before(todayDate);
        } catch (ExpiredJwtException e) {
            hasTokenExpired = true;
        } catch (SignatureException ex) {
            System.out.println("Invalid JWT Signature");
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT Token");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty");
        }
        return hasTokenExpired;

    }

    // validate password reset token
    public boolean hasPasswordResetTokenExpired(String token) {
        boolean hasTokenExpired = false;
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SecurityConstants.PASSWORD_RESET_TOKEN_SECRET)
                    .parseClaimsJws(token).getBody();
            Date tokeExpirationDate = claims.getExpiration();
            Date todayDate = new Date();
            hasTokenExpired = tokeExpirationDate.before(todayDate);
        } catch (ExpiredJwtException e) {
            hasTokenExpired = true;
        } catch (SignatureException ex) {
            System.out.println("Invalid JWT Signature");
        } catch (MalformedJwtException ex) {
            System.out.println("Invalid JWT Token");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            System.out.println("JWT claims string is empty");
        }
        return hasTokenExpired;

    }

    // get userId from authToken
    public String getUserIdFromAuthToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(SecurityConstants.AUTH_TOKEN_SECRET).parseClaimsJws(token).getBody();
        return (String) claims.get("userId");
    }

    // get userId from emailVerificationToken -> ? use get subject
    public String getUserIdFromEmailVerificationToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(SecurityConstants.EMAIL_VERIFICATION_TOKEN_SECRET).parseClaimsJws(token).getBody();
        return (String) claims.get("userId");
    }

    // get userId from passwordResetToken -> ? use get subject
    public String getUserIdFromPasswordResetToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(SecurityConstants.PASSWORD_RESET_TOKEN_SECRET).parseClaimsJws(token).getBody();
        return (String) claims.get("userId");
    }

}
