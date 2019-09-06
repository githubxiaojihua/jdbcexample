package com.xiaojihua.chapter02datasorece;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 知识点;
 * 使用DBCP连接池构建工具类，DBCP是JAVA数据库连接池javax.sql.DataSource规范的
 * 实现者，具体的实现类是BasicDataSource。JDBC提供了
 * BasicDataSourceFactory.createDataSource(properties);
 * 工厂方法来创建BasicDataSource，并且通过配置文件来配置连接信息
 */
public class C01JDBCDataSourceUtile {
    //DataSource是javax.sql.DataSource包下面的，是数据库连接池的一下规范
    private static DataSource dataSource = null;

    static{
        try{
            Properties properties = new Properties();
            //这种情况下中有在properties在项目根目录下时候才能加载到
            //properties.load(new FileInputStream("datasource.properties"));
            /*
                当properties在src下面时可以用下面这一句，通过类加载器的getResourceAsStream()
                方法，可以加载从classpath下进行查找。
                如果properties放到了resources中那么可以发直接只写名字就行，因为resources中的文件
                maven也会放到classpath下面。
                如果properties是在其它的包中那么可以写包的全限定名，比如：
                com/xiaojihua/chapter02datasorece/datasource.properties

             */
            InputStream in = C01JDBCDataSourceUtile.class.getClassLoader().getResourceAsStream("datasource.properties");
            System.out.println(in);
            properties.load(in);
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        }catch(Exception e){
            /*
                这里如果用 throw new RuntimeException(e)
                会导致错误提示不是很准确，因为RuntimeException是不会被try catch捕获的
                （虽然在使用类的main方法中进行了try catch）
                再这里thorw RuntimeException会直接结束程序，并输出错误，但错误的提示信息
                并不是直接打印时候的信息，直接打印的信息更准确。
                这里只是打印报错信息，并不包含错误的栈信息
             */
            System.out.println(e);
            //throw new RuntimeException(e);
            //这个是打印报错信息的栈信息，也就是通常看到的报错
            e.printStackTrace();
        }
    }

    /**
     * 从连接池中获取连接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    /**
     * 关闭所有该关闭的
     * @param conn
     * @param statement
     * @param rs
     */
    public static void closeAll(Connection conn, Statement statement, ResultSet rs){
        try{
            if(conn != null){
                conn.close();
            }

            if(statement != null){
                statement.close();
            }

            if(rs != null){
                rs.close();
            }
        }catch(Exception e){
            System.out.println(e);
        }

    }

}
