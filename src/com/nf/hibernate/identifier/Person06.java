package com.nf.hibernate.identifier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.TableGenerator;


/**
 * Table 样式生成器的创建示例， JPA 语法
 */
@Entity
public class Person06 {

    @Id
    @GeneratedValue(generator = "yyy")
    @TableGenerator(name = "yyy", table = "tb_yyy", allocationSize = 1, valueColumnName = "xyz")
    private long id;
    private String name;

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
}
