package com.nf.hibernate.inheritance;

import javax.persistence.Entity;

@Entity
public class Pig extends Animal {
    private int shiliang;

    public int getShiliang() {
        return shiliang;
    }

    public void setShiliang(int shiliang) {
        this.shiliang = shiliang;
    }
}
