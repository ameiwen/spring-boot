package com.base.boot.service;

import com.base.boot.utils.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService {

    Result login(String username, String password, HttpServletRequest request, HttpServletResponse response);
}
