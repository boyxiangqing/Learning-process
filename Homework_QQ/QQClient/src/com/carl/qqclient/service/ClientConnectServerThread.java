package com.carl.qqclient.service;

import com.carl.qqcommon.Message;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * @author Carl
 * @version 1.0
 */
public class ClientConnectServerThread extends Thread{
    //该线程需要持有Socket
    private Socket socket;

    //构造器可以接受一个Socket对象

    public ClientConnectServerThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        //因为Thread需要在后台和服务器通信,因此我们while循环
        while(true){
            System.out.println("等待接收线程从服务器发送的消息");
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();//如果服务器没有发送Message对象,线程会阻塞在这里

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    //为了方便得到Socket
    public Socket getSocket() {
        return socket;
    }
}
