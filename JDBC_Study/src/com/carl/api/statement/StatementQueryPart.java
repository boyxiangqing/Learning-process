package com.carl.api.statement;

/**
 * @author Carl
 * @version 1.0
 */

import java.sql.*;

/**
 * TODO:
 *      DriverManager
 *      Connection
 *      Statement
 *      ResultSet
 */
public class StatementQueryPart {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        //1.注册驱动
        /**
         * TODO:
         *      注册驱动
         *      依赖:驱动版本是8+ com.mysql.cj.jdbc.Driver
         *          驱动版本是5+ com.mysql.jdbc.Driver
         */
        //DriverManager.registerDriver(new Driver());
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.获取链接
        /**
         * TODO:
         *      java程序要和数据库创建链接!
         *      java程序,链接数据库,肯定是调用某个方法,方法也需要填入连接数据库的基本信息;
         *          数据库ip地址:127.0.0.1
         *          数据库端口号:3306
         *          账号:root
         *          密码:****
         *          链接数据库的名称:atguigu
         */

        /**
         * 参数1:url
         *      jdbc:数据库厂商名://ip地址:port/数据库名
         *      jdbc:mysql://127.0.0.1:3306/atguigu
         * 参数2:username 数据库软件的账号
         * 参数3:password 数据库软件的密码
         */

        // java.sql接口 = 实现类
        Connection connection = DriverManager
                .getConnection("jdbc:mysql://127.0.0.1:3306/atguigu", "root", "123456");
        //3.创建statement
        Statement statement = connection.createStatement();
        //4.发送sql语句,并且返回结果
        String sql = "select * from t_user";
        ResultSet resultSet = statement.executeQuery(sql);
        //5.进行结果解析
        //看看有没有下一行数据,有,你就可以获取
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String account = resultSet.getString("account");
            String password = resultSet.getString("password");
            String nickname = resultSet.getString("nickname");
            System.out.println(id+"---"+account+"---"+password+"---"+nickname);
        }
        //6.关闭资源
        resultSet.close();
        statement.close();
        connection.close();

    }
}
