package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.User;
import com.lagou.domain.UserVO;

import java.util.List;

public interface UserService {
    /*用户分页，多条件组合查询*/
    public PageInfo<User> findAllUserByPage(UserVO userVO);
}
