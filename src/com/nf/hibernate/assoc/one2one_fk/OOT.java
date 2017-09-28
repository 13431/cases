package com.nf.hibernate.assoc.one2one_fk;

import com.nf.hibernate.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OOT {

    @Test
    public void testNewData () {
        IdCard c1 = new IdCard("6563553563x", "斗门公安局", null, null);
        IdCard c2 = new IdCard("6563553564x", "香洲公安局", null, null);

        session.save(c1);
        session.save(c2);


        Person p1 = new Person("李白", null, c1);
        Person p2 = new Person("鲁班", null, c2);
//        Person p3 = new Person("嬴政", null, c2);
        session.save(p1);
        session.save(p2);
//        session.save(p3);
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
