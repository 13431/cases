package com.nf.hibernate.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class SessionUtil {

    private SessionFactory sessionFactory;
    private static SessionUtil instance;

    private SessionUtil() {
        /* hibernate3or5 */
        Configuration configuration = new Configuration();
        configuration.configure("com/nf/hibernate/util/hibernate.cfg.xml");
        sessionFactory = configuration.buildSessionFactory();

        /* hibernate4 */
        /**
        Configuration configure = new Configuration().configure();
        // 创建服务管理中心，可以对 hibernate 的功能进行定制
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configure.getProperties()).build();
        // 利用 serviceRegister 创建 sessionFactory
        sessionFactory = configure.buildSessionFactory(serviceRegistry);
        **/
    }

    public static SessionUtil getInstance() {
        if(instance == null) {
            instance = new SessionUtil();
        }
        return instance;
    }

    public static SessionFactory getSessionFactory () {
        return getInstance().sessionFactory;
    }

    public static Session getSession () {
        return getSessionFactory().openSession();
    }
}
