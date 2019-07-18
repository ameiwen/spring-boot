package com.base.boot.controller;

import com.base.boot.service.LoginService;
import com.base.boot.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping(value = "/login")
    public Result login(String username, String password, HttpServletRequest request, HttpServletResponse response){
        return loginService.login(username,password,request,response);
    }

}
