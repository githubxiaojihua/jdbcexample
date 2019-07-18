package com.xiaojihua.chapter06Products.domain;

import java.util.Objects;

public class C01Product {
    private int pid;
    private String pname;
    private double price;
    private boolean flag;
    private String category_id;

    public C01Product(String pname, double price) {
        this.pname = pname;
        this.price = price;
    }

    public C01Product() {
    }

    @Override
    public String toString() {
        return "C01Product{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                ", price=" + price +
                ", flag=" + flag +
                ", category_id='" + category_id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        C01Product that = (C01Product) o;
        return pid == that.pid &&
                Double.compare(that.price, price) == 0 &&
                flag == that.flag &&
                Objects.equals(pname, that.pname) &&
                Objects.equals(category_id, that.category_id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(pid, pname, price, flag, category_id);
    }

    public int getPid() {
        return pid;
    }

    public String getPname() {
        return pname;
    }

    public double getPrice() {
        return price;
    }

    public boolean isFlag() {
        return flag;
    }

    public String getCategory_id() {
        return category_id;
    }
}
