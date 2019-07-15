package com.xiaojihua.chapter02datasorece;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 知识点：
 * 使用C3P0来实现javax.sql.DataSource。
 * 主要的实现类是ComboPooledDataSource，这个类会自动加载在classPath下的c3p0-config.xml(名字是不能改的)文件来读取链接属性。
 */
public class C03C3P0DataSourceUtil {
    //主要的实现类
    //private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

    //可以使用配置文件中的自定义命名来创建dataSource
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource("abcd");

    public static Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }

    public static DataSource getDataSource(){
        return dataSource;
    }

    public static void closeAll(Connection conn, Statement statement, ResultSet rs){
        try{
            if(conn != null){
                conn.close();
            }

            if(statement != null){
                statement.close();
            }

            if(rs != null){
                statement.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
