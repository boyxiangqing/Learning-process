package com.carl.api;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Carl
 * @version 1.0
 */
public class API_ {
    public static void main(String[] args) throws UnknownHostException {

        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);//LAPTOP-2VGT5DNG/172.16.104.44
        InetAddress byName = InetAddress.getByName("LAPTOP-2VGT5DNG");
        System.out.println("hostname="+byName);
    }
}
