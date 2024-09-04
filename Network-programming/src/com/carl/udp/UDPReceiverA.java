package com.carl.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author Carl
 * @version 1.0
 * @date 2023/4/24 23:15
 */
public class UDPReceiverA {
    public static void main(String[] args) throws IOException {
        //1. 创建一个DatagramSocket对象,,准备在9999接收数据
        DatagramSocket socket = new DatagramSocket(9999);
        //2. 构建一个 DatagramPacket对象,准备接收数据
        // UPD协议 最大数据报为64kb
        byte[] buf = new byte[64*1024];
        DatagramPacket packet = new DatagramPacket(buf,buf.length);
        //3.准备接收方法,将通过网络传输的 DatagramPacket对象
        // 填充到 packet对象
        // 提示:当有数据包发送到 本机的9999端口,就会收到数据
        //     当没有数据发送到本机9999就会阻塞等待
        System.out.println("等待接收数据...");
        socket.receive(packet);

        //4.可以把packet 进行拆包,取出数据,并且显示
        int length = packet.getLength();//实际接收数据长度
        byte[] data = packet.getData();//接收到数据
        String s = new String(data,0,length);
        System.out.println(s);

        //5.发送数据到9998
        data = "好的明天见".getBytes();
        packet =
                new DatagramPacket(data,data.length, InetAddress.getByName("172.16.104.68"),9998);
        socket.send(packet);

        socket.close();
    }
}
