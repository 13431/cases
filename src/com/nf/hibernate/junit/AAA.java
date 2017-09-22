package com.nf.hibernate.junit;

public class AAA {

    /**
     *
     * @param str 输入参数，是一个字符串
     * @return 字符串的长度
     */
    public int getLentgh(String str) {
        if(str == null) return 0;
        return str.length();
    }

    /**
     *
     * @param str 输入参数，字符串
     * @return str -> strstr,  abc -> abcabc
     */
    public String doublestring (String str) {
        if(str == null) return null;
        return str + str;
    }

}
