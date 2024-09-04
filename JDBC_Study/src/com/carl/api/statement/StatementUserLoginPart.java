package com.carl.api.statement;


import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author Carl
 * @version 1.0
 *
 * TODO:
 *      1. 明确jdbc的使用流程和详解内部设计api方法
 *      2. 发现问题,引出preparedStatement
 *
 * TODO:
 *      输入账号和密码
 *      进行数据库信息查询(t_user)
 *      反馈登录成功还是登录失败
 *
 * TODO:
 *      1.键盘输入事件,收集账号和密码信息
 *      2.注册驱动
 *      3.获取链接
 *      4.创建Statement
 *      5.发送查询SQL语句,并获取返回结果
 *      6.结果判断,显示登录成功还是失败
 *      7.关闭资源
 */
public class StatementUserLoginPart {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入账号");
        String account = scanner.nextLine();
        System.out.println("请输入密码");
        String password = scanner.nextLine();

        //2.注册驱动
        /**
         * 方案1:
         *      DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver())
         *  问题: 注册两次驱动
         *      1. DriverManager.registerDriver() 方法本身会注册一次
         *      2. Driver.static{ DriverManager.registerDriver() } 静态代码块,也会注册一次
         *  解决:
         *      只触发静态代码块即可!Driver
         *  触发静态代码块:
         *      类加载机制:类加载的时刻,会触发静态代码块!
         *               加载 [class文件 -> jvm虚拟机的class对象]
         *               连接 [验证(检查文件类型) -> 准备(静态变量默认值) -> 解析(触发静态代码块)]
         *               初始化(静态属性赋真实值)
         *    触发类加载:
         *          1. new 关键字
         *          2. 调用静态方法
         *          3. 调用静态属性
         *          4. 接口 1.8 default默认实现
         *          5. 反射
         *          6. 子类触发父类
         *          7. 程序的入口main
         *
         */
        //DriverManager.registerDriver(new Driver());

        //字符串 -> 提取到外部的配置文件 -> 可以在不改变代码的情况下,完成数据库驱动的切换!
        Class.forName("com.mysql.cj.jdbc.Driver");

        //2.获取数据链接
        /**
         * getConnection(1,2,3)方法,是一个重载方法
         * 允许开发者,用不同的形式传入数据库连接的核心参数!
         *
         * 核心属性:
         *      1. 数据库软件所在的主机ip地址:localhost | 127.0.0.1
         *      2. 数据库软件所在的主机端口号: 3306
         *      3. 链接的具体库: atguigu
         *      4. 连接的账号: root
         *      5. 连接的密码: kou123..
         *      6. 可选的信息 没有
         *
         * 三个参数:
         *      String url          语法格式:jdbc:数据库管理名称[mysql/oracle]://ip地址|主机名:port端口号/数据库名
         *      String user         数据库账号
         *      String password     数据库密码
         * 二个参数:
         *      String url          语法格式:jdbc:数据库管理名称[mysql/oracle]://ip地址|主机名:port端口号/数据库名
         *      Properties          存储账号和密码
         *                          Properties类似于Map 只不过key = value 都是字符串形式
         * 一个参数:
         *     String url           jdbc:mysql://localhost:3306/atguigu?user=root&password=kou123..
         */
        Connection connection =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu","root","123456");

//        Properties info = new Properties();
//        info.put("user","root");
//        info.put("password","123456");
//        Connection connection1 =
//                DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/atguigu", info);
//        Connection connection2 =
//                DriverManager.getConnection("jdbc:mysql://localhost:3306/atguigu?user=root&password=123456");
        // 3.创建发送Sql语句的Statement对象
        Statement statement = connection.createStatement();
        //4.发送SQL语句(1.编写SQL语句 2.发送SQL语句)
        String sql = "select * from t_user where account = '"+account+"'and password='"+password+"';";

        /**
         * SQL分类:DDL(容器创建,修改,删除)  DML(插入,修改,删除) DQL(查询) DCL(权限控制) TPL(事务控制语言)
         *
         *  参数: sql 非DQL
         *  返回: int
         *          情况1:DML 返回影响的行数,例如:删除了三条数据return 3;插入了两条return 2;修改了0条 return 0;
         *          情况2:非DML return 0;
         *  int row = executeUpdate(sql)
         *
         *  参数:sql DQL
         *  返回:resultSet 结果封装对象
         *  ResultSet resultSet = executeQuery(sql);
         *
         */

        ResultSet resultSet = statement.executeQuery(sql);

        //5.查询结果集解析 resultSet
        /**
         *
         */
        //while(resultSet.next()){
        //    int id = resultSet.getInt("id");
        //    String account1 = resultSet.getString("account");
        //    String password1 = resultSet.getString("password");
        //    String nickname = resultSet.getString("nickname");
        //    System.out.println(id+"---"+account+"---"+password+"---"+nickname);
        //}
        if(resultSet.next()){
            System.out.println("登录成功");
        }else {
            System.out.println("登录失败");
        }

        //6.关闭连接
        resultSet.close();
        statement.close();
        connection.close();


    }
}
