package com.dursuneryilmaz.duscrumtool.security.jwt;

import com.dursuneryilmaz.duscrumtool.domain.User;
import com.dursuneryilmaz.duscrumtool.security.SecurityConstants;
import com.dursuneryilmaz.duscrumtool.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserService userService, JwtTokenProvider jwtTokenProvider) {
        super(authenticationManager);
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // get Authorization header
        String header = request.getHeader(SecurityConstants.HEADER_STRING);
        // check header is not null and starts with token prefix defined before
        if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(request);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request) {
        String tokenWithHeader = request.getHeader(SecurityConstants.HEADER_STRING);
        if (tokenWithHeader != null) {
           String token = tokenWithHeader.replace(SecurityConstants.TOKEN_PREFIX, "");
            String userId = jwtTokenProvider.getUserIdFromAuthToken(token);
            User user = userService.getUserByUserId(userId);
            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user.getEmail(), null, user.getAuthorities());
            }
            return null;
        }
        return null;
    }
}
