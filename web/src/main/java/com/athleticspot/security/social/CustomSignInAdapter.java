package com.athleticspot.security.social;

import com.athleticspot.security.jwt.TokenProvider;
import io.github.jhipster.config.JHipsterProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.Cookie;

public class CustomSignInAdapter implements SignInAdapter {

    @SuppressWarnings("unused")
    private final Logger log = LoggerFactory.getLogger(CustomSignInAdapter.class);

    private final UserDetailsService userDetailsService;

    private final JHipsterProperties jHipsterProperties;

    private final TokenProvider tokenProvider;


    public CustomSignInAdapter(UserDetailsService userDetailsService, JHipsterProperties jHipsterProperties,
                               TokenProvider tokenProvider) {
        this.userDetailsService = userDetailsService;
        this.jHipsterProperties = jHipsterProperties;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {
        try {
            log.debug("User id: {}", userId);
            log.debug("Connection: {}", connection);
            log.debug("request: {}", request);

            UserDetails user = userDetailsService.loadUserByUsername(userId);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user,
                null,
                user.getAuthorities());
            log.info("Authentication token: {}", authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            String jwt = tokenProvider.createToken(authenticationToken, false);
            log.info("JWT: {}", jwt);
            ServletWebRequest servletWebRequest = (ServletWebRequest) request;
            servletWebRequest.getResponse().addCookie(getSocialAuthenticationCookie(jwt));
        } catch (AuthenticationException ae) {
            log.error("Social authentication error", ae);
            log.trace("Authentication exception trace: {}", ae);
        }
        return "/#/";
    }

    private Cookie getSocialAuthenticationCookie(String token) {
        Cookie socialAuthCookie = new Cookie("social-authentication", token);
        socialAuthCookie.setPath("/");
        socialAuthCookie.setMaxAge(10);
        return socialAuthCookie;
    }
}
