package com.nf.hibernate.assoc.many2one_twoway;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Author {
    @Id
    @GeneratedValue(generator = "aaa")
    @SequenceGenerator(name = "aaa", sequenceName = "seq_author", allocationSize = 1)
    @Column(name = "authorid")
    private long id;

    @Column(nullable = false, length = 30)
    private String name;

    private String phone;

    // 甩手掌柜
    @OneToMany(mappedBy = "author")
    private List<Book> books = new ArrayList<>();

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Author() {
    }
    public Author(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", books=" + books +
                '}';
    }
}
