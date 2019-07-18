package com.base.boot.inteceptor;

import com.base.boot.common.Constants;
import com.base.boot.model.Users;
import com.base.boot.utils.AlgorithmUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CookieInfo {

    private static Logger logger = LogManager.getLogger(CookieInfo.class);

    // 登陆后的用户唯一标识；用户id,会用aes加密
    public static String LOGIN_UUID_COOKIE_NAME = "_userid";

    public static String LOGIN_COOKIE_CUSTOMER_PREFIX = "login:customer:";

    public static int LOGIN_EXPIRE_TIME = 1000 * 10; //  // 登陆失效时间 2小时

    /**
     * 添加登陆时的 cookie
     */
    public static void addLoginCookie(Users users, HttpServletRequest request, HttpServletResponse response) {

        // 删除相关cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (LOGIN_UUID_COOKIE_NAME.equals(cookie.getName())) {
                    if (StringUtils.isNotBlank(Constants.COOKIE_DOMAIN)
                            && !Constants.COOKIE_DOMAIN.contains("$")) {
                        cookie.setDomain(Constants.COOKIE_DOMAIN);
                    }
                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }

        // 设置登陆 cookie 标识
        String toEnc = LOGIN_COOKIE_CUSTOMER_PREFIX + System.currentTimeMillis() + "_" + users.getId();
        String enc = AlgorithmUtil.encryptAESToString(toEnc, Constants.UUID_AES_KEY);
        // cookie 的名称为 _uuid
        Cookie uuidCookie = new Cookie(LOGIN_UUID_COOKIE_NAME, enc);
        if (StringUtils.isNotBlank(Constants.COOKIE_DOMAIN)
                && !Constants.COOKIE_DOMAIN.contains("$")) {
            uuidCookie.setDomain(Constants.COOKIE_DOMAIN);
        }
        uuidCookie.setMaxAge(LOGIN_EXPIRE_TIME);
        uuidCookie.setHttpOnly(true);//设置为true后，JavaScript无法获取
        uuidCookie.setPath("/");
        response.addCookie(uuidCookie);
    }

    /**
     * 移除登陆 cookie
     *
     * @param request
     * @param response
     */
    public static void removeLoginCookie(HttpServletRequest request, HttpServletResponse response) {

        // 删除相关cookie
        Cookie[] cookies = request.getCookies();
        String customerId = StringUtils.EMPTY;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (LOGIN_UUID_COOKIE_NAME.equals(cookie.getName())) {

                    if (LOGIN_UUID_COOKIE_NAME.equals(cookie.getName())) {
                        customerId = cookie.getValue();
                    }
                    if (StringUtils.isNotBlank(Constants.COOKIE_DOMAIN)
                            && !Constants.COOKIE_DOMAIN.contains("$")) {
                        cookie.setDomain(Constants.COOKIE_DOMAIN);
                    }
                    cookie.setValue(null);
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
            if (StringUtils.isNotBlank(customerId)) {
                customerId = AlgorithmUtil.decryptAESToString(customerId, Constants.UUID_AES_KEY);
            }

        }
    }


    /**
     * 获取登陆用户userId
     *
     * @param request
     * @return
     */
    public static String getCustomerIdByCookie(HttpServletRequest request) {
        Cookie[] cookieList = null;
        String customerId = null;
        try {
            if (request.getCookies() != null) {
                cookieList = request.getCookies();
                for (Cookie cookie : cookieList) {
                    if (LOGIN_UUID_COOKIE_NAME.equals(cookie.getName())) {
                        customerId = cookie.getValue();
                    }
                }
            }
        } catch (Exception ex) {
            logger.error(ExceptionUtils.getStackTrace(ex));
        }
        if (StringUtils.isNotBlank(customerId)) {
            String tmp = AlgorithmUtil.decryptAESToString(customerId, Constants.UUID_AES_KEY);
            customerId = tmp.substring(tmp.lastIndexOf("_") + 1);
        }
        return customerId;
    }


//    public static void addLoginUserNameCookie(String userName, HttpServletRequest request, HttpServletResponse response) {
//        // 删除相关cookie
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (LOGIN_COOKIE_USER_NAME.equals(cookie.getName())) {
//                    if (StringUtils.isNotBlank(Constants.COOKIE_DOMAIN)
//                            && !Constants.COOKIE_DOMAIN.contains("$")) {
//                        cookie.setDomain(Constants.COOKIE_DOMAIN);
//                    }
//                    cookie.setValue(null);
//                    cookie.setMaxAge(0);
//                    response.addCookie(cookie);
//                }
//            }
//        }
//
//        try {
//            if (StringUtil.isNotBlank(userName)) {
//                Cookie userNameCookie = new Cookie(LOGIN_COOKIE_USER_NAME, URLEncoder.encode(userName, "utf-8"));
//                userNameCookie.setMaxAge(LOGIN_EXPIRE_TIME);
//                userNameCookie.setPath("/");
//                if (StringUtil.isNotBlank(Constants.COOKIE_DOMAIN)
//                        && !Constants.COOKIE_DOMAIN.contains("$")) {
//                    userNameCookie.setDomain(Constants.COOKIE_DOMAIN);
//                }
//                response.addCookie(userNameCookie);
//            }
//        } catch (Exception e) {
//            logger.error(ExceptionUtils.getStackTrace(e));
//        }
//    }

}
