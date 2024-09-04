package com.carl.qqclient.service;

import com.carl.qqcommon.Message;
import com.carl.qqcommon.MessageType;
import com.carl.qqcommon.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author Carl
 * @version 1.0
 * 该类完成用户登录验证和用户注册等功能
 */
public class UserClientService {
    private User u;//可能会在其他地方用到user信息,因此做出成员属性

    private Socket socket;//可能会在其他地方用到socket信息,因此做出成员属性

    //根据userId 和 pwd 到服务器验证该用户是否合法
    public boolean checkUser(String userId,String pwd) {
        //创建User对象
        u.setUserId(userId);
        u.setPassword(pwd);

        try {
            socket = new Socket(InetAddress.getLocalHost(), 9999);
            //
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(u);//发送User对象

            //读取从服务器回复的Message对象
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message ms = (Message) ois.readObject();

            if(ms.getMesType().equals(MessageType.MESSAGE_LOGIN_SUCCEED)){//登录ok
                //创建一个和服务器端保持通信的线程->创建一个类 ClientConnectServerThread
                new ClientConnectServerThread(socket);
            }else{

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
