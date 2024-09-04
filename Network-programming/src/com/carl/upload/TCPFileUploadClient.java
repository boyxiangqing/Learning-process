package com.carl.upload;

import com.carl.util.StreamUtils;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author Carl
 * @version 1.0
 */
public class TCPFileUploadClient {
    public static void main(String[] args) throws Exception {
        //客户端连接服务端
        Socket socket = new Socket(InetAddress.getLocalHost(), 8888);
        //创建读取磁盘文件的输入流
//        String filePath = "e:/qie.png";
        String filePath = "/Users/kouxiangqing/downloads/下载.png";
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));

        //byte 就是filePath对应的字节数组
        byte[] bytes = StreamUtils.streamToByteArray(bis);

        //通过socket获取到输出流,将bytes数据发送给服务端
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        bos.write(bytes);//将文件对应的字节数组的内容,写入到数据通道


        socket.shutdownOutput();//设置写入数据的结束标记

        //==接收从服务端恢复的消息
        InputStream inputStream = socket.getInputStream();
        //使用StreamUtils 的方法,直接将inputStream 读取到的内容转换成字符串
        String s = StreamUtils.streamToString(inputStream);
        System.out.println(s);
        System.out.println("hello,world");

        //关闭相关流
        inputStream.close();
        bis.close();
        bos.close();
        socket.close();

    }
}
