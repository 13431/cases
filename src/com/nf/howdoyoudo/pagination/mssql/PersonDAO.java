package com.nf.howdoyoudo.pagination.mssql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {


    /**
     * 查询记录总数
     */
    public int getPersonCount() {
        int count = 0;
        String sql = "select count(*) from person";

        try(Connection conn = DBHelper.getConn();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql)) {
            rs.next();
            count = rs.getInt(1);
        } catch (Exception e) {
            // 什么是运行时异常？跟检查异常有什么区别？
            throw new RuntimeException();
        }

        return count;
    }


    /**
     * 查询分页数据
     * @param offset 从第几条开始
     * @param size 取多少条
     * @return 查询到的分页数据集合
     */
    public List<Person> findPersonsInRange(int offset, int size) {
        ArrayList<Person> persons  = new ArrayList<>();

        String wrapped_sql = DBHelper.pageLimitSQL("select id, name, weixin from person", "id", offset, size);

        try(Connection conn = DBHelper.getConn();
            PreparedStatement ps = conn.prepareStatement(wrapped_sql);
        ) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                persons.add(new Person(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }

        return persons;
    }

}
