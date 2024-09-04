package com.carl.homework;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 * @author Carl
 * @version 1.0
 */
public class Homework02SenderB {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(9998);
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入..");
        String question = scanner.next();
        byte[] data = question.getBytes();

        DatagramPacket packet =
                new DatagramPacket(data,data.length, InetAddress.getLocalHost(),8888);
        socket.send(packet);

        byte[] buf = new byte[1024];
        packet = new DatagramPacket(buf,0,buf.length);

        socket.receive(packet);

        int length = packet.getLength();
        data = packet.getData();
        String s = new String(data,0,length);
        System.out.println(s);

        socket.close();
        System.out.println("B端退出");


    }
}
