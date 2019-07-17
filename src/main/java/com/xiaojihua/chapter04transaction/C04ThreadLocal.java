package com.xiaojihua.chapter04transaction;

/**
 * 知识点：
 * ThreadLocal，代表的是线程局部变量。内部实际封装的是一个Map<Thread,Object>
 * 为每一个线程分配一块独立的存储空间，相互之间互不影响
 * ThreadLocal有两个方法：
 * get()以当前Thread为key获取存储的值
 * set(Object)以当前Thread为key设置相关的局部变量
 */
public class C04ThreadLocal {
    public static void main(String[] args){
        //创建一个ThreadLocal
        ThreadLocal<String> tl = new ThreadLocal<>();
        //以当前线程为key设置值为abc
        tl.set("abc");
        /*
            新开启一个线程来访问tl.get()，由于没有以新开启的线程为key设置任何值
            因此返回为null
         */
        new Thread(){
            @Override
            public void run(){
                System.out.println(tl.get());
            }
        }.start();

    }
}
