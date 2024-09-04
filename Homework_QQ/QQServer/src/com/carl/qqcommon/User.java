package com.carl.qqcommon;

import java.io.Serializable;

/**
 * @author Carl
 * @version 1.0
 * @date 2023/4/25 22:45
 * // 表示用户信息
 */
public class User implements Serializable{

    private static final long serialVersionUID = 1L;
    private String userId;//用户Id
    private String password;//用户密码

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
