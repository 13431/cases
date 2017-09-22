package com.nf.hibernate.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

@Entity
@GenericGenerator(name = "bbb", strategy = "uuid")
public class Girl {
//    @GeneratedValue(generator = "aaaaa2")
//    @SequenceGenerator(name = "aaaaa2", sequenceName = "seq_girl", allocationSize = 1)
    @TableGenerator(name = "ccc", valueColumnName = "aaabbbccc", table = "kkk")
    @GeneratedValue(generator = "ccc")
    @Id
    private String id;

    private String name;
    private String address;

    public Girl () {}


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
