package com.xiaojihua.chapter04transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 知识点：
 * 使用原生JDBC和事务来模拟转账
 */
public class C02JDBCaccountDemoTransaction {
    public static void main(String[] args){
        Connection connection = null;
        Statement statement = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysqltest1","root","root");
            statement = connection.createStatement();
            //开户事务
            connection.setAutoCommit(false);

            statement.executeUpdate("update account set money = money - 1000 where id=1");
            //System.out.println(2/0);//模拟异常
            statement.executeUpdate("update account set money = money + 1000 where id=2");
            //提交事务
            connection.commit();

        }catch(Exception e){
            try{
                //回滚事务
                connection.rollback();
            }catch(SQLException e1){
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            try{
                connection.close();
                statement.close();
            }catch(SQLException e){
                e.printStackTrace();
            }

        }

    }
}
