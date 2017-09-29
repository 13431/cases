package com.nf.hibernate.inheritance;

import javax.persistence.Entity;

@Entity
public class BlackCat extends Cat {
    private int xionghandu;

    public int getXionghandu() {
        return xionghandu;
    }

    public void setXionghandu(int xionghandu) {
        this.xionghandu = xionghandu;
    }
}
