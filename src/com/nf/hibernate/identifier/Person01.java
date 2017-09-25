package com.nf.hibernate.identifier;


import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 手动赋予主键的策略
 */
@Entity
public class Person01 {

    @Id
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
