package com.xiaojihua.chapter04transaction;

import com.mchange.v2.c3p0.C3P0ProxyConnection;
import com.xiaojihua.chapter02datasorece.C03C3P0DataSourceUtil;
import org.apache.commons.dbutils.QueryRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 知识点：
 * 使用DBUtils类库进行事务的模拟
 * 1、不能用带参数的QueryRunner构造方法
 * 2、使用带Connection的query.update方法，同一个事务中的Connection应该一致
 *
 *
 * 1.QueryRunner的两个构造:
 *  * 		public QueryRunner(DataSource);//不支持事务
 *  * 			方法:
 *  * 			update(String sql,Object...params)
 *  * 			query(String sql,ResultSetHandler handler,Object...params)
 *  * 		public QueryRunner();//支持事务
 *  * 			update(Connection conn, String sql,Object...params)
 *  * 			query(Connection conn,String sql,ResultSetHandler handler,Object...params)
 *  
 */
public class C03DButilsTransactionDemo {
    public static void main(String[] args){
        Connection conn = null;
        try{
            conn = C03C3P0DataSourceUtil.getConnection();
            //要使用DBUtils类库进行事务，就不能用带DataSource参数的构造函数
            //而将Connection传给具体的query类
            QueryRunner query = new QueryRunner();
            //设置mysql自动提交为false，也就是开户事务
            conn.setAutoCommit(false);
            String sql = "update account set money = money -1000 where id = ?";
            //接收connection，就方保证与同一事务中的其它query用同一个connection不然不能
            //进入同一个事务
            int nums1 = query.update(conn,sql,new Object[]{1});
            System.out.println(1/0);//模拟报错
            sql = "update account set money = money + 1000 where id = ?";
            int nums2 = query.update(conn,sql,new Object[]{2});
            conn.commit();

            if(nums1>0 && nums2 >0){
                System.out.println("转账成功");
            }
        }catch(SQLException e){
            System.out.println("转账出现问题，程序回滚");
            try{
                conn.rollback();
            }catch(SQLException e1){
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally{
            try{
                conn.close();
            }catch(SQLException e){
                e.printStackTrace();
            }

        }


    }
}
