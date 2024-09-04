package com.carl.qqcommon;

/**
 * @author Carl
 * @version 1.0
 * @date 2023/4/25 22:59
 */
public interface MessageType {
    //1.在接口中定义了一些常量
    //2.不同的常量的值,表示不同的消息类型.
    String MESSAGE_LOGIN_SUCCEED = "1";//表示登录成功
    String MESSAGE_LOGIN_FAIL = "2";//表示登录失败
}
