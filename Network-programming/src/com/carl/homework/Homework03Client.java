package com.carl.homework;

import com.carl.util.StreamUtils;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Carl
 * @version 1.0
 */
public class Homework03Client {
    public static void main(String[] args) throws Exception {

        //1.接收用户输入,指定下载文件名
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入下载文件名");
        String downLoadFileName = scanner.next();

        //2. 客户端连接服务器,准备发送
        Socket socket = new Socket(InetAddress.getLocalHost(),8888);
        //3.获取和Socket关联的输出流
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write(downLoadFileName.getBytes());
        //设置 写入结束
        socket.shutdownOutput();

        //4.读取服务端返回的文件(字节数据)
        BufferedInputStream bis =
                new BufferedInputStream(socket.getInputStream());
        byte[] bytes = StreamUtils.streamToByteArray(bis);

        //5.得到一个输出流准备将bytes的内容写到磁盘
        String filePath = "e:\\"+downLoadFileName+".mp3";
        BufferedOutputStream bufferedOutputStream =
                new BufferedOutputStream(new FileOutputStream(filePath));
        bufferedOutputStream.write(bytes);

        //6.关闭相关流
        bufferedOutputStream.close();
        bis.close();
        outputStream.close();
        socket.close();
        System.out.println("客户端下载完毕");
    }
}
