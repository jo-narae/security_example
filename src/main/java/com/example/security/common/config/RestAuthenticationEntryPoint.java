package com.example.security.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component("restAuthenticationEntryPoint")
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getOutputStream().println(
                "{" +
                        "\"message\": \"" + authenticationException.getMessage() + "\"," +
                        "\"timestamp\": \"" + new Date() + "\"," +
                        "\"status\": " + HttpServletResponse.SC_UNAUTHORIZED + "," +
                        "\"error\": \"Unauthorized\"," +
                        "\"exception\": \"" + authenticationException + "\"" +
                        "}"
        );
    }
}