package com.nf.hibernate.scottdemo.test;

import com.nf.hibernate.scottdemo.Department;
import com.nf.hibernate.scottdemo.Employee;
import com.nf.hibernate.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

/**
 * 建立双边关系
 * 1. 为 dept 表创建实体类 Department
 * 2. 为 emp 表创建实体类 Employee
 * 3. 因为 dept/emp 是多对一的关系，所以，我们决定为两个实体类创建双边关系
 *    首先, 在 Employee 类中，增加 Department 的属性，并注解为 @ManyToOne
 *    其次，在 Department 类中，增加 List<Employee> 属性，并注解为 @OneToMany
 *    然后，为 Employee 的 department 增加 @JoinColumn 注解，定制外键的值
 *    再次，在 Department 类的 employees 属性上，增加 mappedBy，将其作为从端
 * 4. 关系创建完毕，就可以用相关的方法测试了。
 */

public class EmpTest {

    /**
     * 指定上级
     */
    @Test
    public void testAssignManager () {
        Department sbb = session.get(Department.class, 80L);
        Employee sj = session.get(Employee.class, 7934L);

        for (Employee employee : sbb.getEmployees()) {
            employee.setManager(sj);
            session.saveOrUpdate(sj);
        }

    }

    /* 查询 KING 的所有组员 */
    @Test
    public void testQueryKingsXiaoDi () {

//        Employee king = (Employee) session.createQuery("from Employee where name = :name")
//                .setParameter("name", "KING").uniqueResult();

        Employee king = session.get(Employee.class, 7839L);

        System.out.println("我是：" + king.getName());
        System.out.println("我的小弟有：");

        List<Employee> xiaodis = king.getXiaodis();

        for (Employee xiaodi : xiaodis) {
            System.out.printf("   >> 我是 %s, 我的部门是 %s, 我的小弟有 %d 个\n",
                    xiaodi.getName(),
                    xiaodi.getDepartment().getName(),
                    xiaodi.getXiaodis().size());
        }
    }


    @Test
    public void testInsert() {
        Department d = new Department("随便部", "火星镇");

        session.save(d);

        Employee mr = new Employee("鸣人", "火影", 22f, 0f, new Date(), d);
        Employee zz = new Employee("佐助", "流浪", 12f, 5f, new Date(), d);

        session.save(mr);
        session.save(zz);
    }


    @Test
    public void testQueryMany () {
        Department department = session.get(Department.class, 80L);
        System.out.println(" 部门名称： " + department.getName());
        System.out.println(" 部门地址： " + department.getLocation());
        System.out.println(" 部门员工：");
        // for in
        for (Employee employee : department.getEmployees()) {
            System.out.println("   - " + employee);
        }

    }

    @Test
    public void testQuery () {
        List<Employee> employees = session.createQuery("from Employee where salary > 2000").list();

        System.out.printf("一共有 %d 个员工:\n", employees.size());

        for (Employee employee : employees) {
            System.out.printf("   - 员工编号：%d, 员工名称：%s, 员工工资：%.2f元, 员工部门：%s\n",
                    employee.getEmpno(),
                    employee.getName(),
                    employee.getSalary() + employee.getCommission(),
                    employee.getDepartment().getName());
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
