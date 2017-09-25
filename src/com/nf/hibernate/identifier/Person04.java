package com.nf.hibernate.identifier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * 使用生成器，必须先定义
 * 可以在 {@link Entity} 上面定义，也可以在属性上面定义
 */
@Entity
public class Person04 {

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
