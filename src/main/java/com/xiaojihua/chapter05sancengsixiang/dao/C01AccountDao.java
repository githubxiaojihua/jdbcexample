package com.xiaojihua.chapter05sancengsixiang.dao;

import com.xiaojihua.chapter02datasorece.C03C3P0DataSourceUtil;
import com.xiaojihua.chapter05sancengsixiang.utils.C01ConnectionManager;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 用来完成与数据库的交互
 * dao层的异常往外抛
 *
 */
public class C01AccountDao {

    /**
     * 扣款
     * @param fromA
     * @param money
     * @throws SQLException
     */
    public void fromAccount(String fromA, double money) throws SQLException {
        Connection conn = C01ConnectionManager.getConnection();
        QueryRunner query = new QueryRunner();
        query.update(conn,"update account set money = money - ? where aname = ?",money,fromA);
    }

    /**
     * 增加存款
     * @param toA
     * @param money
     * @throws SQLException
     */
    public void toAccount(String toA, double money)throws SQLException{
        Connection conn = C01ConnectionManager.getConnection();
        QueryRunner query = new QueryRunner();
        query.update(conn, "update account set money = money + ? where aname = ?",money,toA);
    }
}
