package com.nf.hibernate.assoc.embed;


import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String country;

    private String province;

    private String detail;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
