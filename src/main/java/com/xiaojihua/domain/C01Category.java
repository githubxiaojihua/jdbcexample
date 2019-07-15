package com.xiaojihua.domain;

import java.util.Objects;

/**
 * 对应数据库中的catetory表：
 * CREATE TABLE `category` (
 *   `cid` int(11) NOT NULL AUTO_INCREMENT,
 *   `cname` varchar(100) DEFAULT NULL,
 *   PRIMARY KEY (`cid`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
 */
public class C01Category {
    private int cid;
    private String cname;

    public C01Category(int cid, String cname) {
        this.cid = cid;
        this.cname = cname;
    }

    public C01Category() {
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "C01Category{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        C01Category that = (C01Category) o;
        return cid == that.cid &&
                Objects.equals(cname, that.cname);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cid, cname);
    }
}
