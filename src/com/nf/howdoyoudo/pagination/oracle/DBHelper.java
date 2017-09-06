package com.nf.howdoyoudo.pagination.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBHelper {


    /**
     * 获取 Oracle 的连接
     * @return 数据库连接
     */
    public static Connection getConn () throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/orcl", "vip", "vip");
    }

    /**
     * limit for oracle pagination
     * @param sql 需要查询的语句
     * @param offset 数据的初始序号
     * @param size 要查询多少条
     * @return 返回带分页的查询语句
     */
    public static String pageLimitSQL(String sql, int offset, int size) {
        String sql_templ = "select * from ( select aaa.*, rownum rn from (%s) aaa where rownum < %d) where rn >= %d";
        return String.format(sql_templ, sql, offset + size, offset);
    }


    public static void main(String[] args) throws Exception {
        Connection conn = DBHelper.getConn();
        String originSql = "select ename, sal from emp order by sal desc";
        String newSql = DBHelper.pageLimitSQL(originSql, 1, 4);
        try(PreparedStatement ps = conn.prepareStatement(newSql);
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                System.out.printf("姓名: %s,   工资: %d\n", rs.getString(1), rs.getInt(2));
            }
        }
        System.out.println(conn);
        conn.close();
    }
}
