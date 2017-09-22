package com.nf.hibernate.junit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class AAATest {

    static AAA aaa = null;

    @BeforeClass
    public static void initsksss () {
        System.out.println("<<<<<<<<");
        aaa = new AAA();
    }

    @AfterClass
    public static void afksjdfksdjf () {
        System.out.println(">>>>>>>>>");
    }

    @Test
    public void testGetLentgh () {
        System.out.println("length");
        assert aaa.getLentgh(null) == 0;
        assert aaa.getLentgh("") == 0;
        assert aaa.getLentgh("hello") == 5;
    }

    @Test
    public void testDoublestring () {
        System.out.println("double");
        assert aaa.doublestring(null) == null;
        assert aaa.doublestring("acb").equals("acbacb");
        assert aaa.doublestring("你好").equals("你好你好");
    }
}
