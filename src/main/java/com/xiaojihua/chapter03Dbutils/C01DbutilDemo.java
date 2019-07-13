package com.xiaojihua.chapter03Dbutils;

import com.xiaojihua.chapter02datasorece.C03C3P0DataSourceUtil;
import org.apache.commons.dbutils.QueryRunner;
import java.sql.SQLException;

/**
 * 知识点：
 * DBUitls框架介绍：
 * DBUtils类，主要负责关闭链接、释放资源、开启事物等
 * QueryRunner类，负责对数据库的CRUD操作，是核心类
 * ResultSetHandler类，结果集处理类，帮助我们处理结果集，封装数据
 *
 * QueryRunner的使用：
 * 构造:
 * 		public QueryRunner(DataSource ds);//需要一个连接池
 * 		public QueryRunner();//不需要连接,用来进行事务操作
 * 	方法:
 * 		 int update(String sql, Object... params);//主要执行增删改
 * 		query(String sql, ResultSetHandler<T> rsh, Object... params);//主要执行查询
 */
public class C01DbutilDemo {
    public static void main(String[] args){
        //insert();
        //delete();
        update();
    }

    public static void insert(){
        try{
            QueryRunner queryR = new QueryRunner(C03C3P0DataSourceUtil.getDataSource());
            String sql = "INSERT INTO users(uname,pass) VALUES(?,?)";
            int nums = queryR.update(sql,new Object[]{"zhangqi","cckk"});
            System.out.println("成功插入" + nums + "条记录");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void delete(){
        try{
            QueryRunner queryR = new QueryRunner(C03C3P0DataSourceUtil.getDataSource());
            String sql = "DELETE FROM users WHERE uid = ?";
            int nums = queryR.update(sql,new Object[]{"4"});
            System.out.println("成功删除" + nums + "条记录");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void update(){
        try{
            QueryRunner queryR = new QueryRunner(C03C3P0DataSourceUtil.getDataSource());
            String sql = "UPDATE users SET uname = ? WHERE uid = ?";
            int nums = queryR.update(sql,new Object[]{"11122","5"});
            System.out.println("成功更新" + nums + "条记录");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
