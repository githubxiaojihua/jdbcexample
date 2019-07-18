package com.xiaojihua.chapter06Products.view;

import com.xiaojihua.chapter06Products.domain.C01Product;
import com.xiaojihua.chapter06Products.service.C01ProductService;

import java.util.Scanner;

public class C01ProductView {
    private static C01ProductService service = new C01ProductService();
    public static void main(String[] args){
        C01ProductService service = new C01ProductService();
        System.out.println("输入以下命令进行操作：");
        while(true){
            System.out.println("C:创建 U:修改 D:删除 DA:删除所有 I:通过ID查询 FA:查询所有 Q:推出");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            switch(input.toUpperCase()){
                case "C":
                    //创建
                    addProduct();
                    break;
                case "U":
                    //修改
                    System.out.println("修改");
                    break;
                case "D":
                    //修改
                    System.out.println("删除");
                    break;
                case "DA":
                    //修改
                    System.out.println("删除所有");
                    break;
                case "I":
                    //修改
                    System.out.println("通过ID查询");
                    break;
                case "FA":
                    //修改
                    System.out.println("查询所有");
                    break;
                case "Q":
                    //修改
                    System.out.println("退出");
                    System.exit(0);
                    break;
                default:
                    //默认操作
                    System.out.println("未选择任何操作");
            }
        }

    }

    public static void addProduct(){
        System.out.println("请输入商品名称：");
        Scanner scanner = new Scanner(System.in);
        String pName = scanner.nextLine();
        System.out.println("请输入商品价格：");
        double price = scanner.nextDouble();
        C01Product product = new C01Product(pName,price);
        service.addProduct(product);
        System.out.println("添加成功");
    }


}
