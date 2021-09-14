package com.sty.component;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sty.util.AESUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Order(2)
public class ApiKeyFilter extends OncePerRequestFilter {

    private static Logger logger = LoggerFactory.getLogger(ApiKeyFilter.class);

    @Autowired
    private ApiKeyBean bean;

    @Override
    public void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse,
            FilterChain filterChain) throws ServletException, IOException {
        logger.info("HTTP request filter to handle X-API-KEY");

        String apiKey = servletRequest.getHeader("X-API-KEY");
        if (!"OPTIONS".equalsIgnoreCase(servletRequest.getMethod())
                && !bean.getApiKey().equals(AESUtil.decrypt(apiKey))) {
            servletResponse.setStatus(403);

            logger.info("X-API-KEY is incorrect, return 403");
            logger.debug("apiKey from HTTP header: {}", apiKey);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
