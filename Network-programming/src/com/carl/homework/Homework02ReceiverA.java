package com.carl.homework;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.*;

/**
 * @author Carl
 * @version 1.0
 */
public class Homework02ReceiverA {
    public static void main(String[] args) throws IOException {

        //Socket socket = new Socket(InetAddress.getLocalHost(),8888);
        DatagramSocket socket = new DatagramSocket(8888);
        byte[] buf = new byte[64 * 1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);

        socket.receive(packet);

        //接收到之后才能进行拆包
        byte[] data = packet.getData();
        int length = packet.getLength();
        String s = new String(data,0,length);
        String answer ="";
        if ("3".equals(s)){
            answer = "收得";
        }else {
            answer = "发错了";
        }

        data = answer.getBytes();
        packet =
                new DatagramPacket(data, data.length,InetAddress.getLocalHost(),9998);
        socket.send(packet);

        socket.close();
        System.out.println("Receiver退出...");

    }



}
