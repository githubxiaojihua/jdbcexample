package com.xiaojihua.chapter06Products.service;

import com.xiaojihua.chapter02datasorece.C03C3P0DataSourceUtil;
import com.xiaojihua.chapter05sancengsixiang.utils.C01ConnectionManager;
import com.xiaojihua.chapter06Products.dao.C01ProductDao;
import com.xiaojihua.chapter06Products.domain.C01Product;

import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class C01ProductService {
    private C01ProductDao dao = new C01ProductDao();

    public void addProduct(C01Product product){
        try{
            dao.addProduct(product);
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    public C01Product findById(int id){
        C01Product product = null;
        try{
           product  = dao.findById(id);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return product;
    }

    public  List<C01Product> findAll(){
        List<C01Product> products = null;
        try{
            products = dao.findAll();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return products;
    }

    public int editProduct(C01Product product){
        int nums = 0;
        try{
            nums = dao.editProduct(product);
        }catch(Exception e){
            e.printStackTrace();
        }
        return nums;
    }

    public int deleteByid(int pid){
        int nums = 0 ;
        try{
            nums = dao.deleteById(pid);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return nums;
    }

    public void deleteById(List<Integer> pids){
        Connection conn = null;
        try{
            C01ConnectionManager.openTransaction();
            for(int i : pids){
                dao.deleteById(i);
            }
            //System.out.println(1/0);
            C01ConnectionManager.commitTran();
        }catch(SQLException e){
            try{
                System.out.println("删除出错，全部回滚！");
                C01ConnectionManager.rollBack();
            }catch(SQLException e1){
                e1.printStackTrace();
            }

            e.printStackTrace();
        }finally{
            try{
                C01ConnectionManager.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }

    }
}
