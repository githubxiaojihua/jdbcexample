package com.xiaojihua.chapter01jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 知识点：
 * 使用工具类做增减改查
 */
public class C03JDBCUtilDemo {
    public static void main(String[] args){
        insert();
        //delete();
        //update();;
        //select();

    }

    public static void insert(){
        Connection conn = null;
        Statement statement = null;
        try{
            conn = C02JDBCUtil.getConnection();
            statement = conn.createStatement();
            //注意sql语句中values的写法
            int nums = statement.executeUpdate("insert into category(cname) values ('饮料'),('头饰'),('羽毛球')");
            System.out.println("成功插入" + nums + "行。");

        }catch(Exception e){
            throw new RuntimeException(e);
        }finally{
            C02JDBCUtil.clossAll(conn,null,statement);
        }
    }

    public static void delete(){
        Connection conn = null;
        Statement statement = null;
        try{
            conn = C02JDBCUtil.getConnection();
            statement = conn.createStatement();
            int nums = statement.executeUpdate("delete from category where cname = '饮料'");
            System.out.println("成功删除" + nums + "条记录！");
        }catch(Exception e){
            throw new RuntimeException(e);
        }finally{
            C02JDBCUtil.clossAll(conn,null,statement);
        }
    }

    public static void update(){
        Connection conn = null;
        Statement statement = null;
        try{
            conn = C02JDBCUtil.getConnection();
            statement = conn.createStatement();
            int num = statement.executeUpdate("update category set cname = '说说' where cid=11");
            System.out.println("成功更新" + num + "条记录");
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public static void select(){
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            conn = C02JDBCUtil.getConnection();
            statement = conn.createStatement();
            resultSet = statement.executeQuery("select * from category");
            while(resultSet.next()){
                Object cid = resultSet.getObject("cid");
                Object cname = resultSet.getObject("cname");
                System.out.println(cid + "\t" + cname);
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally{
            C02JDBCUtil.clossAll(conn,resultSet,statement);
        }
    }
}
