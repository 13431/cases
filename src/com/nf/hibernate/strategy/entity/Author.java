package com.nf.hibernate.strategy.entity;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.*;

@Entity
public class Author extends BaseEntity {

    // 默认的策略是 立刻检索
    private Date birth;

    // 默认的策略是 立刻检索(Eager)，而且是通过 JOIN 语句(Join策略)的形式进行的查询
    // 定制：
    //   when fetch 去设置
    //   how @Fetch 去设置
    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    private Company company;

    // 默认的策略是 延时加载，通过另外发送一条单独的 SELECT 语句进行的查询
    @OneToMany(mappedBy = "author")
    @Fetch(FetchMode.JOIN)
    private List<Game> games = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    @Fetch(FetchMode.JOIN)
    private Set<Book> books = new HashSet<>();

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getBirth() {
        return birth;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
