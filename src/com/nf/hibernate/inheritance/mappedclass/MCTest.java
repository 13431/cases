package com.nf.hibernate.inheritance.mappedclass;

import com.nf.hibernate.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;

public class MCTest {

    @Test
    @Ignore
    public void testNewData () {

        Boy boy = new Boy();
        boy.setName("王新伟");
        boy.setBirth(new Date());
        boy.setAddress("湖南楼顶");
        session.save(boy);

        Girl girl = new Girl();
        girl.setName("小珠海");
        girl.setBirth(new Date());
        girl.setPhone("1111111");
        girl.setWeixin("333333333");
        session.save(girl);

    }

    @Test
    public void firstTest() {
        System.out.println("ssssshahahahahaha");
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
