package com.base.boot.service;


import com.base.boot.model.Users;
import com.base.boot.utils.Result;
import com.github.pagehelper.Page;


public interface UserService {

    Result selectUsersList(Page<Users> page);

}
