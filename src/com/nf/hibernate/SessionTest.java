package com.nf.hibernate;


import com.nf.hibernate.entity.Fish;
import com.nf.hibernate.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SessionTest {

    private Session session = null;
    private Transaction transaction = null;


    @Test
    public void testDoWork() {
        session.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                // 可以将 jdbc 的 Connection 对象暴露出来
                PreparedStatement ps = connection.prepareStatement("SELECT * FROM fish");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getString(2));
                }
                rs.close();
                ps.close();
            }
        });
    }

    @Test
    public void testUpdate() {
        Fish fish = session.get(Fish.class, 10l);

        session.clear();

        fish.setPrice(777);

        session.flush();

        session.merge(fish);
    }

    @Test
    public void testSave() {
        Fish jinyu = new Fish("jinyu", 111.1f);
        // session.save(jinyu);
        jinyu.setId(22222l);
        session.persist(jinyu); // jpa 给保存的函数起的名字
    }

    @Test
    public void testClear() {
        System.out.println("111");
        Fish fish01 = session.get(Fish.class, 10l);
        System.out.println("---------------");

        // session.refresh(fish01);
        // session.clear();
        session.evict(fish01);
        Fish fish02 = session.get(Fish.class, 10l);
        System.out.println("222");
    }

    @Test
    public void testRefresh() {
        Fish fish = session.get(Fish.class, 10l);

        session.refresh(fish);
        System.out.println(fish.getPrice());
    }

    @Test
    public void testFLush() {
        Fish liyu = new Fish("liyu", 222f);
        session.save(liyu);

        session.flush();

        // session.createQuery("from Fish where id = 10").uniqueResult();
        session.get(Fish.class, 10l).getName();

        System.out.println("ok");
    }

    @Test
    public void testLoad() {
        Fish f1 = session.load(Fish.class, 101l);
        System.out.println(f1);
    }

    @Test
    public void testGet() {
        // 1. get
        // Fish f1 = (Fish) session.get("edu.nf.aaa.entity.Fish", 10l);
        Fish f2 = (Fish) session.get(Fish.class, 101l);
        System.out.println(f2);
    }

    @Test
    public void testQuery() {
        Query query = session.createQuery("from Fish where id = :id");
        query.setLong("id", 101l);
        Fish f1 = (Fish) query.uniqueResult();
        System.out.println(f1);
    }


    @Test
    public void testGetLoadQuery() {

        System.out.println("----- query --------");
        session.createQuery("from Fish where id = :id").setLong("id", 10l).uniqueResult();


        System.out.println("----- load --------");
        Fish fish1 = session.load(Fish.class, 10l);

        System.out.println("----- get --------");
        session.get(Fish.class, 10l);
        session.get(Fish.class, 10l);
        session.get(Fish.class, 10l);
        session.get(Fish.class, 10l);
        session.get(Fish.class, 10l);
        session.get(Fish.class, 10l);
        session.get(Fish.class, 10l);
        session.get(Fish.class, 10l);
        session.get(Fish.class, 10l);
        session.load(Fish.class, 10l).getId();
        session.load(Fish.class, 10l).getId();
        session.load(Fish.class, 10l).getId();
        session.load(Fish.class, 10l).getId();
        session.load(Fish.class, 10l).getId();
        session.get(Fish.class, 10l);
    }


    @Test
    public void testLazyOrNot() {
        System.out.println("----- load --------");
        Fish fish1 = session.get(Fish.class, 10l);

        session.close();

        System.out.println(fish1.getName());
    }


    @Test
    public void testIsExist() {
        // Fish fish = session.load(Fish.class, 111l);
        Fish fish = session.get(Fish.class, 111l);
        System.out.println(fish);
    }

    @Test
    public void testFishSaveOrUpdate() {
        // a. 创建工厂
        // b. 获取连接
        // c. 开启事务
        Session session = SessionUtil.getSession();
        session.beginTransaction();

        // 得到了一个瞬时对象
        Fish no01 = new Fish("鲤鱼", 18);

        // 开始持久化，但是在 commit 之前，并没有真正提交到数据库
        // 这一步，只是获得并赋予了 id 值
        session.persist(no01);

        // 手动刷新
        session.flush();

        // commit 的时候，才会真正的保存到数据库
        // 因为，在 commit 的时候，会触发 flush 操作，它会将 session 缓存中的数据同步到数据库内
        session.getTransaction().commit();

        // 关闭 session
        session.close();

        session = SessionUtil.getSession();
        session.beginTransaction();
        no01.setPrice(33.3f);
        session.saveOrUpdate(no01);
        session.getTransaction().commit();
        session.close();
    }



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