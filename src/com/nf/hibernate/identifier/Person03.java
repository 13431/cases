package com.nf.hibernate.identifier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 也可以手动指定生成策略，JPA 语法
 * 手动指定，分为两种：
 * 1. 指定既定策略，即 strategy = GenerationType.Identity/sequence/table
 * 2. 指定生成器，即 generator = "xxx"
 */
@Entity
public class Person03 {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
