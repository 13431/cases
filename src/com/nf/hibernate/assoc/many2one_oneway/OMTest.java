package com.nf.hibernate.assoc.many2one_oneway;

import com.nf.hibernate.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class OMTest {


    @Test
    public void initData() {

        Author luxun = new Author();
        luxun.setName("鲁迅");
        luxun.setPhone("110");

        session.save(luxun);

        Book book1 = new Book("呐喊", 12f);
        Book book2 = new Book("彷徨", 11f);

        book1.setAuthor(luxun);
        book2.setAuthor(luxun);

        session.save(book1);
        session.save(book2);

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
