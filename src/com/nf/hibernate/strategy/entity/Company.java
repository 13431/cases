package com.nf.hibernate.strategy.entity;


import javax.persistence.Entity;

@Entity
public class Company extends BaseEntity {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
