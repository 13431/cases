package com.nf.hibernate.scottdemo;


import javax.persistence.*;

@Entity
@Table(name = "dept")
public class Department {

    @Id
    @GeneratedValue(generator = "aaa")
    @SequenceGenerator(name = "aaa", sequenceName = "seq_dept", allocationSize = 1)
    private long deptno;

    @Column(name = "dname")
    private String name;

    @Column(name = "loc")
    private String location;

    public Department() {
    }

    public Department(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public long getDeptno() {
        return deptno;
    }

    public void setDeptno(long deptno) {
        this.deptno = deptno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptno=" + deptno +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
