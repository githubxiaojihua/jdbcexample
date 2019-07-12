package com.xiaojihua.chapter01jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 * 知识点：
 * SQL注入与防御
 * 通过PreparedStatement（Statement的子类）来进行SQL注入的防御
 * 内部会自动对sql语句进行转译,让这个sql语句中的和sql语法有关的字符都失效
 *
 * PreparedStatement pst = conn.PrepareStatement(sql语句);//注意 sql语句中不要写具体的参数
 * 通过set方法为pst中的sql语句设置具体的值
 * pst.setXxxx(第几个?号,具体的值);//?号从1开始
 *
 */
public class C04SQLInjection {

    public static void main(String[] args){
        Connection conn = null;
        try{
            conn = C02JDBCUtil.getConnection();
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入username:");
            String username = scanner.nextLine();
            System.out.println("请输入paww:");
            String pass = scanner.nextLine();

            sqlInject(conn,username,pass);
            sqlNoInject(conn,username,pass);

        }catch(Exception e){
            System.err.println(e);
        }

    }

    /**
     * 如果用户在输入密码的时候 输入 dflj' or '1'='1 这就是SQL注入
     * 这样用户名和密码不管是什么都会返回登录成功
     * @param conn
     * @param username
     * @param pass
     * @throws Exception
     */
    public static void sqlInject(Connection conn, String username, String pass) throws Exception{
        Statement statement = conn.createStatement();
        String sql = "select * from users where username = '" + username + "' and pass = '" + pass + "'";
        ResultSet rs = statement.executeQuery(sql);
        if(rs.next()){
            System.out.println("无防注入登录成功");
        }else{
            System.out.println("无防注入登录失败");
        }
        statement.close();
        rs.close();
    }

    public static void sqlNoInject(Connection conn, String username, String pass) throws Exception{
        String sql = "select * from users where username = ? and pass = ?";
        PreparedStatement pStatement = conn.prepareStatement(sql);
        pStatement.setObject(1,username);
        pStatement.setObject(2,pass);
        ResultSet rs = pStatement.executeQuery();
        if(rs.next()){
            System.out.println("登录成功");
        }else{
            System.out.println("登录失败");
        }
    }
}
