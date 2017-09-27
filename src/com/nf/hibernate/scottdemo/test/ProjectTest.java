package com.nf.hibernate.scottdemo.test;

import com.nf.hibernate.scottdemo.Employee;
import com.nf.hibernate.scottdemo.Project;
import com.nf.hibernate.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ProjectTest {

    /* 查询员工负责的项目 */
    @Test
    public void testMyProjects () {
        Employee mr = session.load(Employee.class, 7978L);
        for (Project project : mr.getProjects()) {
            System.out.println(project.getName());
        }

        System.out.println("==============");
        for (Employee employee : session.load(Project.class, 1L).getWorkers()) {
            System.out.println(employee.getName());
        }
    }

    /* 为项目指派负责人 */
    @Test
    public void testAssinProject() {
        Project five = session.load(Project.class, 2L);
        List<Employee> workers = five.getWorkers();
        workers.add(session.load(Employee.class, 7566L));
        workers.add(session.load(Employee.class, 7978L));
        workers.add(session.load(Employee.class, 7979L));
        workers.add(session.load(Employee.class, 7902L));
        session.saveOrUpdate(five);


        Project tx = session.load(Project.class, 1L);
        List<Employee> workers1 = tx.getWorkers();
        workers1.add(session.load(Employee.class, 7978L));
        workers1.add(session.load(Employee.class, 7979L));
        workers1.add(session.load(Employee.class, 7839L));
        session.update(tx);
    }


    /* 查询测试 */
    @Test
    public void testQuery () {
        List<Project> projects = (List<Project>) session.createQuery("from Project").list();
        for (Project project : projects) {
            System.out.printf("名字：%s,  目标：%s,  负责了 %d 个项目.\n",
                    project.getName(),
                    project.getAim(),
                    project.getWorkers().size());
        }
    }

    /* 初始化数据 */
    @Test
    public void initProject() {
        session.save(new Project("糖心甜品", "立足世界，走出银河"));
        session.save(new Project("50号商城", "脚踏京东，干翻淘宝"));
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
