package com.nf.hibernate.strategy.entity;


import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Game extends BaseEntity {

    private Float score;

    @ManyToOne
    private Author author;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }
}
