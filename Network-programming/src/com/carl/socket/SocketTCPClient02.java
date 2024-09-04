package com.carl.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author Carl
 * @version 1.0
 */
public class SocketTCPClient02 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(),9999);


        OutputStream outputStream = socket.getOutputStream();
        //outputStream.write("hello,server".getBytes());
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        bufferedWriter.write("hello,server 字符流" );
        bufferedWriter.newLine();//插入换行符,表示写入结束 ,但要求对方使用readline读取
        bufferedWriter.flush();//如果使用的字符流,需要手动刷新,否则数据不会写入数据通道

        //设置结束标记
        socket.shutdownOutput();
        InputStream inputStream = socket.getInputStream();


        byte[] buf =  new byte[1024];
        int len = 0;
        while((len = inputStream.read(buf)) != -1){
            System.out.println(new String(buf,0,len));
        }

        inputStream.close();
        outputStream.close();
        socket.close();
    }
}
