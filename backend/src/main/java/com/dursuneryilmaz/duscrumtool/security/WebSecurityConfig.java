package com.dursuneryilmaz.duscrumtool.security;

import com.dursuneryilmaz.duscrumtool.security.jwt.JwtAuthenticationEntryPoint;
import com.dursuneryilmaz.duscrumtool.security.jwt.JwtAuthenticationFilter;
import com.dursuneryilmaz.duscrumtool.security.jwt.JwtAuthorizationFilter;
import com.dursuneryilmaz.duscrumtool.security.jwt.JwtTokenProvider;
import com.dursuneryilmaz.duscrumtool.service.UserService;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // use our user service interface which extends springs UserDetailsService interface
    private final JwtAuthenticationEntryPoint unAuthenticatedHandler;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public WebSecurityConfig(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder, JwtAuthenticationEntryPoint unAuthenticatedHandler, JwtTokenProvider jwtTokenProvider) {
        this.userService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.unAuthenticatedHandler = unAuthenticatedHandler;
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }

    // changes authentication process url
    public JwtAuthenticationFilter getAuthenticationFilter() throws Exception {
        final JwtAuthenticationFilter authenticationFilter = new JwtAuthenticationFilter(jwtTokenProvider, userService, authenticationManager());
        authenticationFilter.setFilterProcessesUrl(SecurityConstants.LOGIN_URI);
        return authenticationFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unAuthenticatedHandler)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().headers().frameOptions().sameOrigin() //To enable H2 Database
                .and().authorizeRequests()
                .antMatchers(HttpMethod.POST, SecurityConstants.REGISTER_URI).permitAll()
                .antMatchers(HttpMethod.GET, SecurityConstants.EMAIL_VERIFICATION_URI).permitAll()
                .antMatchers(HttpMethod.POST, SecurityConstants.PASSWORD_RESET_REQUEST_URI).permitAll()
                .antMatchers(HttpMethod.POST, SecurityConstants.PASSWORD_RESET_URI).permitAll()
                .antMatchers(SecurityConstants.H2_CONSOLE_URI).permitAll()
                .anyRequest().authenticated();
        http.addFilterBefore(getAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new JwtAuthorizationFilter(authenticationManager(), userService, jwtTokenProvider), BasicAuthenticationFilter.class);
    }
}
