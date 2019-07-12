package com.xiaojihua.chapter01jdbc;

import java.io.FileReader;
import java.sql.*;
import java.util.Properties;

/**
 * 简单的JDBC工具类
 */
public class C02JDBCUtil {
    private static String driverName = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;

    static{
        try{
            //读取配制文件
            Properties properties = new Properties();
            properties.load(new FileReader("H:\\jdbcexample\\src\\main\\resources\\jdbc_config.properties"));
            driverName = properties.getProperty("driverName");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");

            Class.forName(driverName);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection(){
        Connection conn = null;
        try{
            conn = DriverManager.getConnection(url,username,password);
        }catch(Exception e){
            throw new RuntimeException(e);
        }

        return conn;

    }

    public static void clossAll(Connection conn, ResultSet rs, Statement statement){
        try{
            if(conn != null){
                conn.close();
            }

            if(rs != null){
                rs.close();
            }

            if(statement != null){
                statement.close();
            }
        }catch(SQLException exception){
            throw new RuntimeException(exception);
        }


    }

}
