package com.xiang.spring6.service.impl;

import com.xiang.spring6.dao.UserDao;
import com.xiang.spring6.dao.impl.UserDaoImpl;
import com.xiang.spring6.service.UserService;

public class UserServiceImpl implements UserService {

    UserDao dao = new UserDaoImpl();
    public void deleteByUser() {

         dao.deleteById();
    }
}
