package com.nf.hibernate.strategy.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Chapter extends BaseEntity {
    private int count;

    @ManyToOne
    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
