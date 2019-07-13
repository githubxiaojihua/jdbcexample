package com.xiaojihua.chapter02datasorece;

import com.xiaojihua.chapter01jdbc.C02JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class C02JDBCDataSourceUtileDemo {

    public static void main(String[] args){
        //insert();
        //delete();
        //update();
        select();
    }

    public static void insert(){
        Connection conn = null;
        PreparedStatement psd = null;

        try{
            conn = C01JDBCDataSourceUtile.getConnection();
            String sql = "insert into users(username,pass) values(?,?)";
            psd = conn.prepareStatement(sql);
            psd.setObject(1,"weiliu");
            psd.setObject(2,"ccc");
            int nums = psd.executeUpdate();
            System.out.println("插入成功" + nums + "行");
        }catch(Exception e){
            System.out.println(e);
        }finally{
            C01JDBCDataSourceUtile.closeAll(conn,psd,null);
        }

    }

    public static void delete(){
        Connection conn = null;
        PreparedStatement psd = null;
        try{
            conn = C01JDBCDataSourceUtile.getConnection();
            String sql = "delete from users where id = ?";
            psd = conn.prepareStatement(sql);
            psd.setObject(1,5);
            int nums = psd.executeUpdate();
            System.out.println("删除成功" + nums + "条");
        }catch(Exception e){
            System.out.println(e);
        }finally{
            C01JDBCDataSourceUtile.closeAll(conn,psd,null);
        }
    }

    public static void update(){
        Connection conn = null;
        PreparedStatement psd = null;
        try{
            conn = C01JDBCDataSourceUtile.getConnection();
            String sql = "update users set pass = ? where id = ?";
            psd = conn.prepareStatement(sql);
            psd.setObject(1,"zsf");
            psd.setInt(2,4);
            int nums = psd.executeUpdate();
            System.out.println("更新成功" + nums + "条");
        }catch(Exception e){
            System.out.println(e);
        }finally{
            C01JDBCDataSourceUtile.closeAll(conn,psd,null);
        }
    }

    public static void select(){
        Connection conn = null;
        PreparedStatement psd = null;
        ResultSet rs = null;
        try{
            conn = C01JDBCDataSourceUtile.getConnection();
            String sql = "select * from users where uname like ?";
            psd = conn.prepareStatement(sql);
            //注意这个参数的%%
            psd.setObject(1,"%l%");
            rs = psd.executeQuery();
            while(rs.next()){
                String username = rs.getString("uname");
                String pass = rs.getString("pass");
                System.out.println(username + "\t" + pass);
            }
        }catch(Exception e){
            System.out.println(e);
        }finally{
            C01JDBCDataSourceUtile.closeAll(conn,psd,rs);
        }
    }
}
