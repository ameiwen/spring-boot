package com.base.boot.service.impl;

import com.base.boot.dao.UsersMapper;
import com.base.boot.inteceptor.CookieInfo;
import com.base.boot.model.Users;
import com.base.boot.service.LoginService;
import com.base.boot.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public Result login(String username, String password, HttpServletRequest request, HttpServletResponse response) {
        if(username.equals("admin") && password.equals("123456")){
            Users users = usersMapper.selectByPrimaryKey(1);
            CookieInfo.addLoginCookie(users,request,response);
            Result result = Result.ok();
            result.put("user",users);
            return  result;
        }
        return Result.error("账号或密码错误");
    }
}
