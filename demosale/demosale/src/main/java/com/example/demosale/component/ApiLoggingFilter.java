package com.example.demosale.component;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.AbstractRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

@Component
public class ApiLoggingFilter extends AbstractRequestLoggingFilter {
    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        if(!logger.isInfoEnabled()){
            return;
        }
        logger.info(String.format("%s %s", request.getMethod(), request.getRequestURI()));
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message){}
}
