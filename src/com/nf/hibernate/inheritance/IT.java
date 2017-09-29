package com.nf.hibernate.inheritance;

import com.nf.hibernate.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class IT {

    @Test
    @Ignore
    public void firstTest () {
        Dog dog = new Dog();
        dog.setName("旺财");
        dog.setSex("雄性");
        dog.setCategory("二哈");
        dog.setZhongchengdu(99);
        session.save(dog);

        Cat cat = new Cat();
        cat.setName("谢贺丰");
        cat.setSex("雌性");
        cat.setCategory("波斯");
        cat.setColor("red");
        cat.setYanzhi(98);
        session.save(cat);

        BlackCat cat1 = new BlackCat();
        cat1.setName("小黑");
        cat1.setSex("女");
        cat1.setCategory("警察");
        cat1.setColor("white");
        cat1.setYanzhi(11);
        cat1.setXionghandu(99999);
        session.save(cat1);

        Pig pig = new Pig();
        pig.setName("馒头");
        pig.setSex("雌性");
        pig.setCategory("荷兰猪");
        pig.setShiliang(11);
        session.save(pig);

        Animal animal = new Animal();
        animal.setName("外星人");
        animal.setSex("未知");
        animal.setCategory("异形");
        session.save(animal);
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
