package com.nf.hibernate.assoc.many2one_twoway;


import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(generator = "bbb")
    @SequenceGenerator(name = "bbb", sequenceName = "seq_book", allocationSize = 1)
    @Column(name = "bookid")
    private long id;
    private String name;
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book() {
    }

    public Book(String name, float price, Author author) {
        this.name = name;
        this.price = price;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @PrePersist
    public void dododo () {
        System.out.println("================-----------------=================");
    }
}
