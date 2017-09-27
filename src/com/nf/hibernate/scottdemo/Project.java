package com.nf.hibernate.scottdemo;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "proj")
public class Project {
    @Id
    @GeneratedValue(generator = "ccc")
    @SequenceGenerator(name = "ccc", sequenceName = "seq_proj", allocationSize = 1)
    @Column(name = "pid")
    private long id;

    @Column(name = "pname", nullable = false, length = 40)
    private String name;

    private String aim;

    @ManyToMany
    @JoinTable(  // 可以省略，如果省略的话，会采用默认值
            name = "proj_emp",
            joinColumns = @JoinColumn(name = "pid"),
            inverseJoinColumns = @JoinColumn(name = "empno")
    )
    private List<Employee> workers = new ArrayList<>();

    public Project() {
    }

    public Project(String name, String aim) {
        this.name = name;
        this.aim = aim;
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

    public String getAim() {
        return aim;
    }

    public void setAim(String aim) {
        this.aim = aim;
    }

    public List<Employee> getWorkers() {
        return workers;
    }

    public void setWorkers(List<Employee> workers) {
        this.workers = workers;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", aim='" + aim + '\'' +
                '}';
    }
}
