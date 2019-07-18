package com.xiaojihua.chapter06Products.service;

import com.xiaojihua.chapter06Products.dao.C01ProductDao;
import com.xiaojihua.chapter06Products.domain.C01Product;

import java.sql.SQLException;

public class C01ProductService {
    private C01ProductDao dao = new C01ProductDao();

    public void addProduct(C01Product product){
        try{
            dao.addProduct(product);
        }catch(SQLException e){
            e.printStackTrace();
        }

    }
}
