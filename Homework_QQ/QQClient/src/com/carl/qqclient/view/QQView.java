package com.carl.qqclient.view;

import com.carl.qqclient.utils.Utility;

/**
 * @author Carl
 * @version 1.0
 */
public class QQView {
    private boolean loop = true;
    private String key = "";

    public static void main(String[] args) {
        new QQView().mainMenu();
    }
    private void mainMenu(){
        while(loop){
            System.out.println("========欢迎登录网络通信系统=======");
            System.out.println("\t\t 1 登录系统");
            System.out.println("\t\t 9 退出系统");

            key = Utility.readString(1);

            //根据用户的输入,来处理不同的逻辑
            switch (key){
                case "1":
                    System.out.print("请输入用户号:");
                    String userId = Utility.readString(50);
                    System.out.print("请输入密码:");
                    String pwd = Utility.readString(50);
                    //去服务端验证用户是否合法
                    if(true){
                        System.out.println("========欢迎"+userId+"登录网络通信系统=======");
                        while(loop){
                            System.out.println("========欢迎"+userId+"登录网络二级菜单=======");
                            System.out.println("\t\t 1 显示在线用户列表");
                            System.out.println("\t\t 2 群发消息");
                            System.out.println("\t\t 3 私聊消息");
                            System.out.println("\t\t 4 发送文件");
                            System.out.println("\t\t 9 退出系统");
                            System.out.print("请输入你的选择");
                            key = Utility.readString(1);
                            switch (key){
                                case "1":
                                    System.out.println("显示在线用户列表");
                                    break;
                                case "2":
                                    System.out.println("群发消息");
                                    break;
                                case "3":
                                    System.out.println("私聊消息");
                                    break;
                                case "4":
                                    System.out.println("发送文件");
                                    break;
                                case "9":
                                    loop = false;
                                    break;
                            }
                        }
                    }else {
                        System.out.println("登录失败");
                    }
                    break;
                case "9" :
                    System.out.println("退出==");
                    loop = false;
            }
        }
    }
}
