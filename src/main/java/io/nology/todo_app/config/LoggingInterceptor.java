package io.nology.todo_app.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoggingInterceptor implements HandlerInterceptor {
    // Create an instance of the logger to write logs
    private static final Logger logger = LogManager.getLogger(LoggingInterceptor.class);

    @Override
    // preHandle is called before the request is handled by the controller
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("Incoming request: {} {}", request.getMethod(), request.getRequestURI());
        return true;
    }

    @Override
    // afterCompletion is called after the request has been handled by the controller
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("Completed request: {} {} with status {}", request.getMethod(), request.getRequestURI(), response.getStatus());
    }
}
