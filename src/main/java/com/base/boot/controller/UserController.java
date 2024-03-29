package com.base.boot.controller;

import com.base.boot.model.Users;
import com.base.boot.service.UserService;
import com.base.boot.utils.Result;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/userList")
    public Result userList(Page<Users> page,Users users){
        return userService.selectUsersList(page,users);
    }

}
