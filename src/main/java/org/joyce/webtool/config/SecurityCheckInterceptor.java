package org.joyce.webtool.config;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Joyce on 14-4-21.
 */

public class SecurityCheckInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if (request.getRequestURL().toString().contains("Error"))
            return true;

        if (request.getRequestURL().toString().contains("login"))
            return true;

        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/securityCheckError");
            return false;
        }
        return true;
    }

}
