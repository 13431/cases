package com.nf.howdoyoudo.pagination.mssql;


/**
 * 数据库中对应的表为：
 * -- create table person (id int primary key, name varchar(20) not null, weixin varchar(30));
 *
 * 调用 {@link DBHelper#initData()} 初始化数据
 */
public class Person {

    private int id;
    private String name;
    private String weixin;

    public Person() {
    }

    public Person(int id, String name, String weixin) {
        this.id = id;
        this.name = name;
        this.weixin = weixin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }


    @Override
    public String toString() {
        return "Person01{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weixin='" + weixin + '\'' +
                '}';
    }
}
