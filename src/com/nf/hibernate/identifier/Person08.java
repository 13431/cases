package com.nf.hibernate.identifier;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 下面这种写法等同于 GenerationType.SEQUENCE,
 * 但这是 hibernate 的语法
 */
@Entity
public class Person08 {

    @Id
    @GeneratedValue(generator = "xxs")
    @GenericGenerator(name = "xxs", strategy = "sequence")
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
