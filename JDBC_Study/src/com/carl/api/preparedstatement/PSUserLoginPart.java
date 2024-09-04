package com.carl.api.preparedstatement;

import java.sql.*;
import java.util.Scanner;

/**
 * @author Carl
 * @version 1.0
 *
 * TODO: 防止注入攻击 | 演示 ps 的使用流程
 *
 */
public class PSUserLoginPart {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1.收集用户信息
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入账号");
        String account = scanner.nextLine();
        System.out.println("请输入密码");
        String password = scanner.nextLine();

        //2.ps的数据库流程
        //(1) 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        //(2) 获取连接
        Connection connection =
                DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/atguigu", "root", "123456");

        /**
         *  statement
         *          1.创建statement
         *          2.拼接SQL语句
         *          3.发送SQL语句,并且返回获取结果
         *
         *  preparedStatement
         *          1.编写SQL语句结果,不包含动态值部分的语句,动态值部分使用占位符 ? 替代
         *          2.创建preparedStatement,并且传入动态值
         *          3.动态值 占位符 赋值 ? 单独赋值即可
         *          4.发送SQL语句即可,并获取返回结果
         *
         */
        //(3) 编写SQL语句结果
        String sql = "select * from t_user where account = ? and password = ?;";
        //(4) 创建预编译preparedStatement
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //(5) 传入动态值
        preparedStatement.setObject(1, account);
        preparedStatement.setObject(2, password);

        //(6) 发送SQL语句,并获取返回结果
        ResultSet resultSet = preparedStatement.executeQuery();
        //(7) 遍历结果集
        if (resultSet.next()) {
            System.out.println("登录成功");
        }else {
            System.out.println("登录失败");
        }

        //(8) 关闭资源
        resultSet.close();
        preparedStatement.close();
        connection.close();



    }
}
