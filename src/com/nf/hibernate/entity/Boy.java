package com.nf.hibernate.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Boy", schema = "vip")
public final class Boy {

    @Id
    @Column(name = "boy_id")
    @GeneratedValue
    private long id;

    @Basic
    @Column(nullable = false, length = 20, unique = true)
    private String name;

    @Basic
    private int age;

    @Basic
    private String weixin;

    @Basic
    private String phone;

    @Basic
    private String address;

    @Temporal(TemporalType.DATE)
    private Date birth;

    @Transient
    private String hahaah;

    @OneToOne
    private Girl myGirlFriend;




    public long getId() {
        return id;
    }

    public void setId(long bid) {
        this.id = bid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.trim().length() < 3) {
            throw new RuntimeException("名字不合法");
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getHahaah() {
        return name + ":" + address;
    }

    public void setHahaah(String hahaah) {
        this.hahaah = hahaah;
    }

    public Girl getMyGirlFriend() {
        return myGirlFriend;
    }

    public void setMyGirlFriend(Girl myGirlFriend) {
        this.myGirlFriend = myGirlFriend;
    }

}
