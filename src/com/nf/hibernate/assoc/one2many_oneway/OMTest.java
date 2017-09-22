package com.nf.hibernate.assoc.one2many_oneway;

import com.nf.hibernate.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class OMTest {


    @Test
    public void testGetBook () {
        Book book = session.get(Book.class, 1l);
    }

    @Test
    public void initDate () {

        Book book1 = new Book("呐喊", 12f);
        Book book2 = new Book("彷徨", 11f);


        Author author = new Author();
        author.setName("鲁迅");
        author.setPhone("110");

        List<Book> books = author.getBooks();
        books.add(book1);
        books.add(book2);

        session.save(author);

        session.save(book1);
        session.save(book2);
    }

    @Test
    public void firstTest () {
        initDate();
        Author luxun = session.get(Author.class, 1l);
        List<Book> booksList = luxun.getBooks();
        System.out.println(booksList);

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
