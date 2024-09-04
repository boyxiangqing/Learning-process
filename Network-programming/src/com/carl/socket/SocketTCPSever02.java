package com.carl.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Carl
 * @version 1.0
 */
@SuppressWarnings({"all"})
public class SocketTCPSever02 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        System.out.println("服务器监听9999中...");
        Socket socket = serverSocket.accept();
        System.out.println("服务端 socket="+serverSocket.getClass());

        InputStream inputStream = socket.getInputStream();
        byte[] buf = new byte[1024];
        int len = 0;
        while((len = inputStream.read(buf))!=-1){
            System.out.println(new String(buf,0,len));
        }

        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello,client".getBytes());
        socket.shutdownOutput();

        inputStream.close();
        outputStream.close();
        socket.close();
        serverSocket.close();


    }
}
