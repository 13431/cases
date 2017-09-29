package com.nf.hibernate.strategy;

import com.nf.hibernate.strategy.entity.*;
import com.nf.hibernate.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class STest {

    @Test
    public void testQuery2 () {
        Author author = session.get(Author.class, 2L);
        System.out.println(author);
        System.out.println(author.getBooks().size());
        System.out.println(author.getGames().get(0));
    }

    @Test
    public void testQuery () {
        Author author = session.get(Author.class, 2L);
        System.out.println(author);
        System.out.println(author.getCompany().getName());;
    }


    @Test
    @Ignore
    public void testNewData() {
        Company nfit = new Company();
        nfit.setName("南方男方");
        nfit.setAddress("珠海斗门白蕉镇");
        session.save(nfit);

        Author wyp = new Author();
        wyp.setName("吴英平");
        wyp.setCompany(nfit);
        session.save(wyp);

        Author hjp = new Author();
        hjp.setName("洪俊鹏");
        hjp.setCompany(nfit);
        session.save(hjp);

        Game lola = new Game();
        lola.setName("咯拉");
        lola.setScore(99F);
        lola.setAuthor(wyp);

        Game lola2 = new Game();
        lola2.setName("咯拉2");
        lola2.setScore(80F);
        lola2.setAuthor(wyp);

        Game wzly = new Game();
        wzly.setName("王者泪雨");
        wzly.setScore(100000f);
        wzly.setAuthor(hjp);

        session.save(lola);
        session.save(lola2);
        session.save(wzly);

        Book lolagl = new Book();
        lolagl.setName("lola攻略");
        lolagl.setPrice(222f);
        lolagl.setAuthor(wyp);

        Book lolagl2 = new Book();
        lolagl2.setName("lola不得不知的幕后故事");
        lolagl2.setPrice(666f);
        lolagl2.setAuthor(wyp);

        Book wz = new Book();
        wz.setName("我们不是农药");
        wz.setPrice(444f);
        wz.setAuthor(hjp);

        Book wz2 = new Book();
        wz2.setName("你们管我是不是农药");
        wz2.setPrice(446f);
        wz2.setAuthor(hjp);

        Book wz3 = new Book();
        wz3.setName("其实，我们有点像农药");
        wz3.setPrice(222f);
        wz3.setAuthor(hjp);

        session.save(lolagl);
        session.save(lolagl2);
        session.save(wz);
        session.save(wz2);
        session.save(wz3);

        Chapter c = new Chapter();
        c.setName("2222");
        c.setCount(33333);
        c.setBook(lolagl);

        Chapter c2 = new Chapter();
        c2.setName("444");
        c2.setCount(333223);
        c2.setBook(lolagl);

        Chapter c3 = new Chapter();
        c3.setName("3333");
        c3.setCount(333133);
        c3.setBook(lolagl);

        session.save(c);
        session.save(c2);
        session.save(c3);
    }


    @Test
    @Ignore
    public void testFirst() {
        System.out.println("---------------");
    }




    // ======================
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
