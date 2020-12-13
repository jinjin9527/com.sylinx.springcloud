package com.sylinx.sample1.async;

import com.sylinx.sample1.common.Console;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CompletionLoggingHandlerInterceptor extends HandlerInterceptorAdapter {
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Console.println("afterCompletion");
    }
}