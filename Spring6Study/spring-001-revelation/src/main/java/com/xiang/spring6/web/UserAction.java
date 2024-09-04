package com.xiang.spring6.web;

import com.xiang.spring6.service.UserService;
import com.xiang.spring6.service.impl.UserServiceImpl;

/**
 * 表示层
 */
public class UserAction {
    private UserService userService = new UserServiceImpl();

    /**
     * 删除用户信息的请求
     */
    public void deleteRequest(){
         userService.deleteByUser();
    }

}
