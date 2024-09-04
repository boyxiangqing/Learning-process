package com.carl.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Carl
 * @version 1.0
 */
public class SocketTCP03Server {
    public static void main(String[] args) throws IOException {

            ServerSocket serverSocket = new ServerSocket(9999);
            System.out.println("服务器监听9999中...");
            Socket socket = serverSocket.accept();
            System.out.println("服务端 socket="+serverSocket.getClass());

            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String s = bufferedReader.readLine();
            System.out.println(s);


            OutputStream outputStream = socket.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            bufferedWriter.write("hello client 字符流");
            bufferedWriter.newLine();
            bufferedWriter.flush();



            bufferedReader.close();
            bufferedWriter.close();
            socket.close();
            serverSocket.close();
    }
}
