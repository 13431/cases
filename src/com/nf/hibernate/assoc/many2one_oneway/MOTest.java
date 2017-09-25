package com.nf.hibernate.assoc.many2one_oneway;

import com.nf.hibernate.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MOTest {


    @Test
    public void initData() {

        Author luxun = new Author();
        luxun.setName("鲁迅");
        luxun.setPhone("110");

        session.save(luxun);

        Book book1 = new Book("呐喊", 12f, luxun);
        Book book2 = new Book("彷徨", 11f, luxun);

        session.save(book1);
        session.save(book2);

        session.save(new Book("故事新编", 22f, luxun));
        session.save(new Book("野草集", 43f, luxun));
    }


    @Test
    public void getAuthorBooks() {
        initData();
        session.flush();

        Author author = session.get(Author.class, 1l);
        System.out.println("作者是： " + author.getName());

        List<Book> bookList = session.createQuery("from Book where author.id = :id")
                .setLong("id", author.getId())
                .list();

        System.out.println("他的作品有：" + bookList);
    }

    @Test
    public void getBookAuthor() {
        initData();
        session.flush();

        Book book = session.get(Book.class, 3l);
        System.out.println("《" + book.getName() + "》的作者是：" + book.getAuthor().getName() + ", 他的电话是：" + book.getAuthor().getPhone());
    }

    @Test
    public void firstTest () {
        Book book1 = new Book("彷徨", 111f);
        session.save(book1);
    }

    private Session session;
    private Transaction transaction;

    @Before
    public void setup() {
        session = SessionUtil.getSession();
        transaction = session.beginTransaction();
    }

    @After
    public void last() {
        if (session.isOpen()) {
            transaction.commit();
            session.close();
        }
    }
}
