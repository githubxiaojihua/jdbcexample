package com.xiaojihua.chapter04transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * 知识点：
 * 使用原生JDBC来模拟转账
 * mysql默认是自动提交的。每条语句都自动提交
 * 在没有事务的时候，当出现问题的时候转账是有问题的。
 */
public class C01JDBCaccountDemo {
    public static void main(String[] args) throws Exception{

        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqltest1","root","root");
        Statement statement = conn.createStatement();
        //虽然有异常但这条语句还是被提交了
        statement.executeUpdate("update account set money = money - 1000 where id=1");
        System.out.println(2/0);//模拟异常
        statement.executeUpdate("update account set money = money + 1000 where id=2");
        conn.close();
        statement.close();

    }
}
