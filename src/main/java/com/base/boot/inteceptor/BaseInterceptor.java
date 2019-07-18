package com.base.boot.inteceptor;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class BaseInterceptor implements HandlerInterceptor {

    private static Logger logger = LogManager.getLogger(BaseInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        boolean isLogin = false;
        String customerId = CookieInfo.getCustomerIdByCookie(request);
        if (StringUtils.isBlank(customerId)) {
            isLogin = false;
        } else {
            isLogin = true;
        }

        if (!isLogin) {
            try {
                response.setContentType("application/json;charset=utf-8");
                response.setStatus(401);
                PrintWriter printWriter = response.getWriter();
                printWriter.write("{\"msg\":\"not login\"}");
            } catch (Exception e) {
                logger.error(ExceptionUtils.getStackTrace(e));
            }
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
