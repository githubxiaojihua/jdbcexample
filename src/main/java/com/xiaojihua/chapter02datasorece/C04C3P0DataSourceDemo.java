package com.xiaojihua.chapter02datasorece;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * c3p0datasource的demo
 */
public class C04C3P0DataSourceDemo {

    public static void main(String[] args){
        insert();
    }

    public static void insert(){
        Connection conn = null;
        PreparedStatement statement = null;

        try{
            conn = C03C3P0DataSourceUtil.getConnection();
            String sql = "INSERT INTO users(uname,pass) VALUES(?,?)";
            statement = conn.prepareStatement(sql);
            statement.setObject(1,"weiliu");
            statement.setObject(2,"abc");
            int nums = statement.executeUpdate();
            System.out.println("成功插入" +  nums  + "条记录");
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            C03C3P0DataSourceUtil.closeAll(conn,statement,null);
        }

    }
}
