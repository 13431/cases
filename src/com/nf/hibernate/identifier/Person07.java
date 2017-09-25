package com.nf.hibernate.identifier;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;


/**
 * hibernate 私有的策略, 非 JPA 支持
 * 比如: increment/native/hilo/uuid/uuid2/seqhilo...
 */
@Entity
public class Person07 {

    @Id
    @GeneratedValue(generator = "zzz")
    @GenericGenerator(name = "zzz", strategy = "uuid2")
    private UUID id;
    private String name;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
