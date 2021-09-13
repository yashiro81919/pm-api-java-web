package com.sty.component;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sty.util.AESUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Order(2)
public class ApiKeyFilter extends OncePerRequestFilter {

    @Autowired
    private ApiKeyBean bean;

    @Override
    public void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse,
            FilterChain filterChain) throws ServletException, IOException {

        String apiKey = servletRequest.getHeader("X-API-KEY");
        if (!"OPTIONS".equalsIgnoreCase(servletRequest.getMethod())
                && !bean.getApiKey().equals(AESUtil.decrypt(apiKey))) {
            servletResponse.setStatus(403);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
