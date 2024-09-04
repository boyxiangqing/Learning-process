package com.carl.homework;


import com.carl.util.StreamUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Carl
 * @version 1.0
 */
public class Homework03Server {
    public static void main(String[] args) throws Exception {
        //1.监听9999端口
        ServerSocket  serverSocket = new ServerSocket(8888);
        System.out.println("8888...");
        //2.等待客户端连接
        Socket socket = serverSocket.accept();
        //3.读取 客户端发送要下载的文件名
        InputStream inputStream = socket.getInputStream();
        byte[] b = new byte[1024];
        int len = 0;
        String downLoadFileName = "";
        while((len = inputStream.read(b))!=-1){
            downLoadFileName+= new String(b,0,len);
        }
        System.out.println("客户端希望下载的文件名:"+downLoadFileName);

        String resFileName = "";
        if(downLoadFileName.equals("高山流水")){
            resFileName ="src/高山流水.mp3";
        }else {
            resFileName ="src/无名.mp3";
        }
        //4.创建一个输入流,读取文件
        BufferedInputStream bufferedInputStream =
                new BufferedInputStream(new FileInputStream(resFileName));

        //5.使用工具类StreamUtils,读取文件到一个字节数组
        byte[] bytes = StreamUtils.streamToByteArray(bufferedInputStream);

        //6.得到Socket关联的输出流
        BufferedOutputStream bufferedOutputStream =
                new BufferedOutputStream(socket.getOutputStream());
        //7.写入到数据通道,返回给客户端
        bufferedOutputStream.write(bytes);
        socket.shutdownOutput();

        //8.关闭相关资源
        bufferedInputStream.close();
        bufferedOutputStream.close();
        inputStream.close();
        socket.close();
        serverSocket.close();


    }
}
