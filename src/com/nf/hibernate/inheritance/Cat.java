package com.nf.hibernate.inheritance;


import javax.persistence.Entity;

@Entity
public class Cat extends Animal {
    private String color;
    private int yanzhi;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYanzhi() {
        return yanzhi;
    }

    public void setYanzhi(int yanzhi) {
        this.yanzhi = yanzhi;
    }
}
