package com.xiaojihua.chapter06Products.dao;

import com.xiaojihua.chapter02datasorece.C03C3P0DataSourceUtil;
import com.xiaojihua.chapter05sancengsixiang.utils.C01ConnectionManager;
import com.xiaojihua.chapter06Products.domain.C01Product;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class C01ProductDao {
    public int deleteById(int id) throws SQLException {
        QueryRunner query = new QueryRunner();
        String sql = "delete from products where pid = ?";
        int nums = query.update(C01ConnectionManager.getConnection(),sql,id);
        return nums;
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

    public C01Product findById(int id) throws SQLException{
        QueryRunner query = new QueryRunner(C03C3P0DataSourceUtil.getDataSource());
        String sql = "select * from products where pid = ?";
        C01Product product = query.query(sql,new BeanHandler<>(C01Product.class),id);
        return product;
    }

    public List<C01Product> findAll() throws SQLException{
        QueryRunner query = new QueryRunner(C03C3P0DataSourceUtil.getDataSource());
        String sql = "select * from products";
        List<C01Product> products = query.query(sql,new BeanListHandler<>(C01Product.class));
        return products;
    }

    public int editProduct(C01Product product) throws SQLException{
        QueryRunner query = new QueryRunner(C03C3P0DataSourceUtil.getDataSource());
        String sql = "update products set pname = ? , price = ? where pid = ?";
        int nums = query.update(sql,new Object[]{product.getPname(),product.getPrice(),product.getPid()});
        return nums;
    }
}
