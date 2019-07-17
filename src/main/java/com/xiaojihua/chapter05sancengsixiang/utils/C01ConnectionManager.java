package com.xiaojihua.chapter05sancengsixiang.utils;

import com.xiaojihua.chapter02datasorece.C03C3P0DataSourceUtil;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 知识点：
 * 通过ThreadLocal来管理里线程的数据库链接，每一个线程都存储一份自己的
 * 数据库链接在ThreadLocal中，然后获取链接、开启事务、提交事务、关闭链接
 * 都可以通过ThreadLocal中的链接来做到。
 */
public class C01ConnectionManager {
    //定义ThreadLocal来存储线程的数据库链接
    private static ThreadLocal<Connection> tl = new ThreadLocal<>();

    /**
     * 获取链接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException{
        Connection conn = tl.get();
        if(conn == null){
            conn = C03C3P0DataSourceUtil.getConnection();
            tl.set(conn);
        }
        return conn;
    }

    /**
     * 开启事务
     * @throws SQLException
     */
    public static void openTransaction() throws SQLException{
        getConnection().setAutoCommit(false);
    }

    /**
     * 关闭事务
     * @throws SQLException
     */
    public static void commitTran() throws SQLException{
        getConnection().commit();
    }

    /**
     * 回滚事务
     * @throws SQLException
     */
    public static void rollBack() throws SQLException{
        getConnection().rollback();
    }

    /**
     * 关闭事务
     * @throws SQLException
     */
    public static void close() throws SQLException{
        getConnection().close();
    }
}
