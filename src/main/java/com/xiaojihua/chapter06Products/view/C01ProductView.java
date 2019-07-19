package com.xiaojihua.chapter06Products.view;

import com.xiaojihua.chapter06Products.domain.C01Product;
import com.xiaojihua.chapter06Products.service.C01ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 项目实例：
 * 通过DBUitls和数据库连接池等知识，建立一个商品管理小项目。
 * 其中删除所有使用了事务。
 * 使用了三层思想
 *
 */
public class C01ProductView {
    private static C01ProductService service = new C01ProductService();
    public static void main(String[] args){
        C01ProductService service = new C01ProductService();
        System.out.println("输入以下命令进行操作：");
        while(true){
            System.out.println("C:创建 U:修改 D:删除 DA:删除所有 I:通过ID查询 FA:查询所有 Q:退出");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            switch(input.toUpperCase()){
                case "C":
                    //创建
                    addProduct();
                    break;
                case "U":
                    //修改
                    edit();
                    break;
                case "D":
                    //删除
                    deleteById();
                    break;
                case "DA":
                    //删除所有
                    deleteBatch();
                    break;
                case "I":
                    //查询
                    findById();
                    break;
                case "FA":
                    //查询所有
                    findAll();
                    break;
                case "Q":
                    //退出
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

    public static void findById(){
        System.out.println("请输入查询的商品编号：");
        Scanner scanner = new Scanner(System.in);
        int pid = scanner.nextInt();
        C01Product product = service.findById(pid);
        System.out.println("查询结果：" + product);
    }

    public static void findAll(){
        List<C01Product> productList = service.findAll();
        System.out.println("查询结果：");
        for(C01Product product : productList){
            System.out.println(product);
        }
    }

    public static void edit(){
        System.out.println("请输入编辑的商品编号：");
        Scanner scanner = new Scanner(System.in);
        /*
            如果这里写了scanner.nextInt()，会导致下面的，nextLine()获取不到
            想要的值，在”请输入修改的商品名“后会直接输出”请输入修改的价格“，
            因为，nextInt()并不会读取完一行，只是读取int长度的字节。
            那么当用户输入数字并回车的时候，实际上是提交了数字和回车两个元素，
            而nextInt只读取了其中一个。而nextLine()会读取当前所在的行（剩下的内容）
            所以就读取到了回车。
         */
        int pid = Integer.parseInt(scanner.nextLine());
        C01Product product = service.findById(pid);
        if(product == null){
            System.out.println("待编辑的商品不存在！");
            return;
        }
        System.out.println("查询结果:" + product);
        System.out.println("请输入修改的商品名:");
        String pname = scanner.nextLine();
        System.out.println("请输入修改的价格：");
        double price = Double.parseDouble(scanner.nextLine());
        product.setPname(pname);
        product.setPrice(price);
        int nums = service.editProduct(product);
        if(nums == 1){
            System.out.println("修改成功！");
        }else{
            System.out.println("修改失败！");
        }

    }

    public static void deleteById(){
        System.out.println("请输入删除的商品编号：");
        Scanner scanner = new Scanner(System.in);
        int pid = Integer.parseInt(scanner.nextLine());
        C01Product product = service.findById(pid);
        if(product == null){
            System.out.println("要删除的商品不存在！");
            return;
        }
        System.out.print("要删除的商品是：" + product);
        System.out.println("您确定要删除吗? y/n");
        String selected = scanner.nextLine().toUpperCase();
        if(selected.equals("Y")){
            int nums = service.deleteByid(pid);
            System.out.println("删除成功！");
        }else{
            System.out.println("取消删除！");
        }

    }

    public static void deleteBatch(){
        System.out.println("进入批量删除模式：（输入-1退出）");
        Scanner scanner = new Scanner(System.in);
        List<Integer> pids = new ArrayList<>();
        C01Product product = null;
        while(true){
            System.out.println("请输入删除的商品编号：");
            int pid = Integer.parseInt(scanner.nextLine());
            if(pid == -1){
                break;
            }
            product = service.findById(pid);
            if(product != null){
                pids.add(pid);
                System.out.println("已标记要删除的商品:" + product);
            }else{
                System.out.println("未找到要删除的商品！");
                continue;
            }
        }
        System.out.println("确定要删除标记的商品吗？ y/n");
        String selected = scanner.nextLine().toUpperCase();
        if(selected.equals("Y")){
            service.deleteById(pids);
            System.out.println("删除成功！");
        }else{
            System.out.println("取消删除！");
        }


    }


}
