package com.nf.howdoyoudo.pagination.mssql;


/**
 * ���ݿ��ж�Ӧ�ı�Ϊ��
 * -- create table person (id int primary key, name varchar(20) not null, weixin varchar(30));
 *
 * ���� {@link DBHelper#initData()} ��ʼ������
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
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weixin='" + weixin + '\'' +
                '}';
    }
}
