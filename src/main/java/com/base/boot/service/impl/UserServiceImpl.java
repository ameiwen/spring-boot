package com.base.boot.service.impl;

import com.base.boot.dao.UsersMapper;
import com.base.boot.model.Users;
import com.base.boot.model.UsersExample;
import com.base.boot.service.UserService;
import com.base.boot.utils.Result;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public Result selectUsersList(Page<Users> page,Users users) {
        try {
            UsersExample example = new UsersExample();
            example.setOrderByClause("id desc");
            UsersExample.Criteria criteria = example.createCriteria();
            criteria.andCreateTimeEqualTo(users.getCreateTime());
            //分页
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
            List<Users> list = usersMapper.selectByExample(example);
            PageInfo<Users> pageInfo = new PageInfo<Users>(list);
            Result result = Result.ok("操作成功");
            result.put("data",pageInfo);
            return result;
        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }
}
