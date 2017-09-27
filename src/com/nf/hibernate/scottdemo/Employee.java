package com.nf.hibernate.scottdemo;

import javax.persistence.*;
import java.util.Date;


/**
 * EMPNO    NUMBER(4)
 ENAME    VARCHAR2(10) Y
 JOB      VARCHAR2(9)  Y
 MGR      NUMBER(4)    Y
 HIREDATE DATE         Y
 SAL      NUMBER(7,2)  Y
 COMM     NUMBER(7,2)  Y
 */
@Entity
@Table(name = "emp")
public class Employee {

    @Id
    @GeneratedValue(generator = "bbb")
    @SequenceGenerator(name = "bbb", sequenceName = "seq_emp", allocationSize = 1)
    @Column(precision = 4)
    private long empno;

    @Column(name = "ename", length = 10, nullable = false)
    private String name;

    private String job;

    @Column(name = "sal")
    private float salary;

    @Column(name = "comm")
    private Float commission;

    @Temporal(TemporalType.DATE)
    private Date hireDate;

    @ManyToOne
    @JoinColumn(name = "deptno")
    private Department department;

    public Employee() {
    }

    public Employee(String name, String job, float salary, Float commission, Date hireDate, Department department) {
        this.name = name;
        this.job = job;
        this.salary = salary;
        this.commission = commission;
        this.hireDate = hireDate;
        this.department = department;
    }

    public long getEmpno() {
        return empno;
    }

    public void setEmpno(long empno) {
        this.empno = empno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Float getCommission() {
        return commission;
    }

    public void setCommission(Float commission) {
        this.commission = commission;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empno=" + empno +
                ", name='" + name + '\'' +
                '}';
    }
}
