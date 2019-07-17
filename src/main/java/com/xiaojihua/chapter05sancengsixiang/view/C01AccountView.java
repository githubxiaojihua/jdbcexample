package com.xiaojihua.chapter05sancengsixiang.view;

import com.xiaojihua.chapter05sancengsixiang.service.C01AccountService;

public class C01AccountView {
    public static void main(String[] args){
        String fromA = "jack";
        String toA = "rose";
        double money = 1000;
        C01AccountService service = new C01AccountService();
        service.transfer(fromA, toA, money);
    }
}
