package com.basf.challenge.security;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SimpleHeaderFilter extends OncePerRequestFilter {

    private final String HEADER_NAME = "BASF-Auth";
    private final String HEADER_VALUE = "secret";
    private final int NOT_AUTHORIZED_STATUS = 401;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        // OPTIONS and Swagger_API should always work
        if (request.getMethod().equals("OPTIONS") ||
                request.getRequestURI().contains("api-docs") || request.getRequestURI().contains("swagger")) {
            filterChain.doFilter(request, response);
            return;
        }
        String val = request.getHeader(HEADER_NAME);
        if (val == null || !val.equals(HEADER_VALUE)) {
            response.setStatus(NOT_AUTHORIZED_STATUS);
            response.getWriter().append("Not authorized");
            return;
        }
        filterChain.doFilter(request, response);
    }

}
