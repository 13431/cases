package com.nf.hibernate.inheritance.mappedclass;


import javax.persistence.Entity;

@Entity
public class Girl extends Person {
    private String weixin;
    private String phone;

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
