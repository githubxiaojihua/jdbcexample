package com.xiaojihua.chapter06Products.dao;

import com.xiaojihua.chapter02datasorece.C03C3P0DataSourceUtil;
import com.xiaojihua.chapter05sancengsixiang.utils.C01ConnectionManager;
import com.xiaojihua.chapter06Products.domain.C01Product;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;

public class C01ProductDao {
    public void deleteById(int id) throws SQLException {
        QueryRunner query = new QueryRunner();
        String sql = "delete from products where pid = ?";
        query.update(C01ConnectionManager.getConnection(),sql,id);
    }

    public void deleteOneById(int id) throws SQLException{
        QueryRunner query = new QueryRunner(C03C3P0DataSourceUtil.getDataSource());
        String sql = "delete from products where pid = ?";
        query.update(sql,id);
    }

    public void addProduct(C01Product product) throws SQLException{
        QueryRunner query = new QueryRunner(C03C3P0DataSourceUtil.getDataSource());
        String sql = "insert into products(pname,price,flag,category_id) values(?,?,?,?)";
        Object[] params = new Object[]{product.getPname(),product.getPrice(),"1","1000"};
        query.update(sql,params);
    }
}
