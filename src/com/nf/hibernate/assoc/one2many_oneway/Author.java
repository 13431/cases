package com.nf.hibernate.assoc.one2many_oneway;


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

    @Column(length = 40, nullable = false)
    private String name;

    @Column(length = 12)
    private String phone;

    @OneToMany
    @JoinColumn(name = "authorid")
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
}
