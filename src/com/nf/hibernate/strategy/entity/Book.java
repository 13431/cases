package com.nf.hibernate.strategy.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Book extends BaseEntity {

    private float price;

    @ManyToOne
    private Author author;

    @OneToMany(mappedBy = "book")
    private List<Chapter> chapters = new ArrayList<>();

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
