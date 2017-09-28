package com.nf.hibernate.assoc.one2one_pk;

import com.nf.hibernate.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OOT {


    @Test
    public void testQuery2() {
        IdCard ppp = session.load(IdCard.class, 1L);
        System.out.println(ppp.getIdcardno());
        System.out.println(ppp.getPerson().getName());
    }

    @Test
    public void testQuery() {
        Person ppp = session.load(Person.class, 1L);
        System.out.println(ppp.getName());
        System.out.println(ppp.getIdCard().getIdcardno());
    }

    @Test
    public void testNewData() {
        IdCard c1 = new IdCard("999", "广州公安局", null, null);
        IdCard c2 = new IdCard("988", "坦洲公安局", null, null);
        session.save(c1);
        session.save(c2);

        Person p1 = new Person("苏菲玛索", null, c1);
        Person p2 = new Person("汤姆汉克斯", null, c2);

        session.save(p1);
        session.save(p2);
    }

    @Test
    public void testFirst() {
        System.out.println("hahahahaha");
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
