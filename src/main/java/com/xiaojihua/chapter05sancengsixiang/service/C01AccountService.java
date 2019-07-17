package com.xiaojihua.chapter05sancengsixiang.service;

import com.xiaojihua.chapter02datasorece.C03C3P0DataSourceUtil;
import com.xiaojihua.chapter05sancengsixiang.dao.C01AccountDao;
import com.xiaojihua.chapter05sancengsixiang.utils.C01ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 业务层，用于处理业务，
 * 利用dao层和C01ConnectionManager来进行业务处理。
 * 高内聚低耦合就是每个类都只完成自己的事情，不涉及其他类的事情。
 *
 * 注意学习C01ConnectionManager类的写法和使用思想
 *
 * service的异常包括dao传过来的异常，必须处理将异常拦截在用户层之前
 */
public class C01AccountService {
    public void transfer(String fromA, String toA, double money){
        C01AccountDao dao = new C01AccountDao();

        try{
            C01ConnectionManager.openTransaction();
            dao.fromAccount(fromA,money);
            System.out.println(1/0);
            dao.toAccount(toA,money);
            C01ConnectionManager.commitTran();
            System.out.println("转账成功");
        }catch(SQLException e){
            System.out.println("转账失败，程序回滚");
            try{
                C01ConnectionManager.rollBack();
            }catch(SQLException e1){
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            try{
                C01ConnectionManager.close();
            }catch(SQLException e){

            }

        }

    }
}
