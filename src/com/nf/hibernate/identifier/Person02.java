package com.nf.hibernate.identifier;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * {@link GeneratedValue} 是用来定制主键策略的, JPA 语法
 * 如果不带参数，那么它默认的策略是 GenerationType.AUTO
 * 让 hibernate 自动选择生成主键的策略
 * 1. 如果连接的数据库支持 identity 或 autoincrement 等关键字，那么使用 GenerationType.Identity 策略
 * 2. 否则，如果数据库支持序列，那么使用 GenerationType.SEQUENCE 策略
 * 3. 否则，即不支持关键字，也不支持序列的一些陈旧数据库，那么使用 GenerationType.TABLE 来模拟序列，作为主键的生成策略
 */
@Entity
public class Person02 {

    @Id
    @GeneratedValue
    // @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
