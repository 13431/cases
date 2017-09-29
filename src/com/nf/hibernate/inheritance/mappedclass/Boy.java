package com.nf.hibernate.inheritance.mappedclass;


import javax.persistence.Entity;

@Entity
public class Boy extends Person {

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
