package com.nf.hibernate.assoc.one2one_fk;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "oo_person")
public class Person {
    @Id @GeneratedValue
    @Column(name = "personid", nullable = false)
    private long id;

    private String name;

    private Date birth;

    public Person() {
    }

    public Person(String name, Date birth, IdCard idCard) {
        this.name = name;
        this.birth = birth;
        this.idCard = idCard;
    }

    @OneToOne(optional = false)  // 将此列设为非空，如果没有为其赋值，会抛异常
    @JoinColumn(name = "cid")
    private IdCard idCard;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birth=" + birth +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public IdCard getIdCard() {
        return idCard;
    }

    public void setIdCard(IdCard idCard) {
        this.idCard = idCard;
    }
}
