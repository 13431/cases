package com.nf.hibernate.scottdemo.test;

import com.nf.hibernate.scottdemo.Department;
import com.nf.hibernate.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DeptTest {

    /**
     * 插入数据
     */
    @Test
    public void testNewDepartment () {
        session.save(new Department("北方IT", "黑龙江漠河"));
    }


    /*

     1. 为表创建了一个序列 seq_dept
        create sequence seq_dept start with 50 increment by 10;

     2. 创建了实体类 Department

     3. 将实体类配置到 hibernate.cfg.xml
        注意，将 hbm2ddl.auto 给注释掉

     4. 搞测试:
       首先，创建 sessionFactory
       其次，获取 session
       然后，通过 Query 对象进行查询
       最后，通过循环，将结果输送出来。

     */
    @Test
    public void testQuery () {

        String hql = "from Department ";
        List<Department> departments = session.createQuery(hql).list();

        for (Department department : departments) {
            System.out.printf(">> 部门编号: %d, 部门名称: %s, 部门地址: %s\n",
                    department.getDeptno(),
                    department.getName(),
                    department.getLocation());
        }
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
