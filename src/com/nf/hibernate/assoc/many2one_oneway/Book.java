package com.nf.hibernate.assoc.many2one_oneway;


import javax.persistence.*;

@Entity
@SequenceGenerator(name = "aaa", sequenceName = "seq_book", allocationSize = 1)
public class Book {
    @Id
    @GeneratedValue(generator = "aaa")
    @Column(name = "bookid")
    private long id;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(nullable = false)
    private float price;

    @ManyToOne
    @JoinColumn(name = "authorid")
    private Author author;

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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Book() {
            }

    public Book(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
