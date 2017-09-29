package com.nf.hibernate.inheritance;

import javax.persistence.Entity;

@Entity
public class Dog extends Animal {
    private int zhongchengdu;

    public int getZhongchengdu() {
        return zhongchengdu;
    }

    public void setZhongchengdu(int zhongchengdu) {
        this.zhongchengdu = zhongchengdu;
    }
}
