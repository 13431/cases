package com.nf.hibernate.identifier;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
 * 使用 JPA 的语法，可以定制 table/sequence 类型的生成器
 * 下面是一个定制 sequence 序列生成器的例子
 */
@Entity
@SequenceGenerator(name = "xxx", schema = "vip", sequenceName = "seq_person05", initialValue = 1, allocationSize = 1)
public class Person05 {

    @Id
    @GeneratedValue(generator = "xxx")
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
