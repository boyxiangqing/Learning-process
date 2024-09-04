package com.carl.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author Carl
 * @version 1.0
 */
public class SocketTCP03Client {
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket(InetAddress.getLocalHost(),9999);
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("hello,server 字符流" );
        bufferedWriter.newLine();//插入换行符,表示写入结束 ,但要求对方使用readline读取
        bufferedWriter.flush();//如果使用的字符流,需要手动刷新,否则数据不会写入数据通道


        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String s = bufferedReader.readLine();
        System.out.println(s);

        bufferedReader.close();
        bufferedWriter.close();
        socket.close();
    }
}
