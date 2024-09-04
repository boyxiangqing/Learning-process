package com.carl.homework;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Carl
 * @version 1.0
 */
public class Homework01Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket socket = serverSocket.accept();
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedInputStream =
                new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedInputStream.readLine();
        String answer = "";
        if ("name".equals(s)){
            answer = "我是Carl";
        }else if("hobby".equals(s)){
            answer = "Java";
        }else {
            answer = "???";
        }
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter =
                new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write(answer);
        bufferedWriter.newLine();
        bufferedWriter.flush();

        bufferedWriter.close();
        inputStream.close();
        socket.close();
        serverSocket.close();
    }
}
