package com.xiaojihua.chapter03Dbutils;

import com.xiaojihua.chapter02datasorece.C03C3P0DataSourceUtil;
import com.xiaojihua.domain.C01Category;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 专门用来执行数据库的查询操作
 *  * 		1.QueryRunner(DataSource)
 *  * 		2.query(String sql, ResultSetHandler<T> rsh, Object... params);//主要执行查询
 *  *
 *  * 		ResultSetHandler:结果集处理类
 *  * 			我们发现ResultSetHandler是一个接口,
 *  * 			而框架中给我们定义了一堆实现类
 *  * 			ArrayHandler:
 *  * 			ArrayListHandler
 *  * 			**BeanHandler
 *  * 			**BeanListHandler
 *  * 			ColumnListHandler
 *  * 			KeyedHandler
 *  * 			MapHandler
 *  * 			MapListHandler
 *  * 			**ScalarHandler
 */
public class C02DbutilDemo2 {

    public static void main(String[] args) throws Exception{
        demo08();

    }

    /**
     * 使用ArrayHandler作为ResultSetHandler，返回的是第一条记录的所有列
     * @throws SQLException
     */
    public static void demo1() throws SQLException {
        QueryRunner query = new QueryRunner(C03C3P0DataSourceUtil.getDataSource());
        String sql = "select * from category";
        //只返回一条记录的所有列
        Object[] objs = query.query(sql,new ArrayHandler());
        for(Object obj : objs){
            System.out.println(obj);
        }
    }

    /**
     * 使用ArrayListHandler作为ResultSetHandler，返回的是所有记录
     * @throws SQLException
     */
    public static void demo2() throws SQLException{
        QueryRunner query = new QueryRunner(C03C3P0DataSourceUtil.getDataSource());
        String sql = "select * from category";
        List<Object[]> objs = query.query(sql,new ArrayListHandler());
        for(Object[] os : objs){
            System.out.println(os[0] + "\t" + os[1]);
        }
    }

    /**
     * 使用BeanHandler作为ResultSetHandler
     * 返回第一条记录并将其封装成指定的Bean
     * @throws SQLException
     */
    public static void demo3() throws SQLException{
        QueryRunner query = new QueryRunner(C03C3P0DataSourceUtil.getDataSource());
        String sql = "select * from category";
        C01Category category = query.query(sql,new BeanHandler<>(C01Category.class));
        System.out.println(category);
    }

    /**
     * 使用BeanListHandler作为ResultSetHandler
     * 返回查询到的所有数据，并将其封装成指定的bean
     * @throws SQLException
     */
    public static void demo4() throws SQLException{
        QueryRunner query = new QueryRunner(C03C3P0DataSourceUtil.getDataSource());
        String sql = "select * from category";
        List<C01Category> categories = query.query(sql,new BeanListHandler<>(C01Category.class));
        for(C01Category cat : categories){
            System.out.println(cat);
        }
    }

    /**
     * 使用ColumnListHandler作为ResultSetHandler
     * 返回查询出的记录中的某一列的所有值
     * @throws SQLException
     */
    public static void demo5() throws SQLException{
        QueryRunner query = new QueryRunner(C03C3P0DataSourceUtil.getDataSource());
        String sql = "select * from category";
        //指定列名，并且根据列的类型指定泛型
        List<String> cnams = query.query(sql,new ColumnListHandler<String>("cname"));
        System.out.println(cnams);
    }

    /**
     * 用map来接收查询出来的第一条记录
     * @throws SQLException
     */
    public static void demo6() throws SQLException{
        QueryRunner query = new QueryRunner(C03C3P0DataSourceUtil.getDataSource());
        String sql = "select * from category";
        Map<String,Object> map = query.query(sql, new MapHandler());
        System.out.println(map);
    }

    /**
     * 用map集合来接收查询出来的所有记录
     * @throws SQLException
     */
    public static void demo7() throws SQLException{
        QueryRunner query = new QueryRunner(C03C3P0DataSourceUtil.getDataSource());
        String sql = "select * from category";
        List<Map<String,Object>> maps = query.query(sql, new MapListHandler());

        System.out.println(maps);
    }

    //ScalarHandler处理类的使用，用于返回单列计算值
    public static void demo08() throws SQLException{
        //1.创建QueryRunner对象
        QueryRunner qr = new QueryRunner(C03C3P0DataSourceUtil.getDataSource());
        //2.执行查询
        String sql = "select count(*) from category";
        Object count = qr.query(sql,new ScalarHandler<Object>());
        //3
        System.out.println(count);
    }

}
