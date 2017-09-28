package com.nf.hibernate.assoc.one2one_pk;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "oo_idcard")
public class IdCard {

    @Id
    @GeneratedValue
    @Column(name = "idcardid")
    private long id;

    @Column(length = 18, nullable = false)
    private String idcardno;

    private String qianfadanwei;

    private Date qianfariqi;

    private Date youxiaoriqi;

    @OneToOne(mappedBy = "idCard")
    private Person person;

    public IdCard() {
    }

    public IdCard(String idcardno, String qianfadanwei, Date qianfariqi, Date youxiaoriqi) {
        this.idcardno = idcardno;
        this.qianfadanwei = qianfadanwei;
        this.qianfariqi = qianfariqi;
        this.youxiaoriqi = youxiaoriqi;
    }


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdcardno() {
        return idcardno;
    }

    public void setIdcardno(String idcardno) {
        this.idcardno = idcardno;
    }

    public String getQianfadanwei() {
        return qianfadanwei;
    }

    public void setQianfadanwei(String qianfadanwei) {
        this.qianfadanwei = qianfadanwei;
    }

    public Date getQianfariqi() {
        return qianfariqi;
    }

    public void setQianfariqi(Date qianfariqi) {
        this.qianfariqi = qianfariqi;
    }

    public Date getYouxiaoriqi() {
        return youxiaoriqi;
    }

    public void setYouxiaoriqi(Date youxiaoriqi) {
        this.youxiaoriqi = youxiaoriqi;
    }
}
