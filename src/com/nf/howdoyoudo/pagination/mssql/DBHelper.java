package com.nf.howdoyoudo.pagination.mssql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;


public class DBHelper {


    /**
     * ͨ�� JDBC ��ȡ���ݿ�����
     *
     * �����֪�� SQLServer �˿ںţ�����ͨ���������鿴��
     * -- exec sys.sp_readerrorlog 0, 1, 'listening'
     *
     * @return SqlServer2008 �����ݿ�����
     */
    public static Connection getConn () throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=pagination", "sa", "123456");
    }


    /**
     * ��ȡһ���������������
     * @param isMale �������ֻ�Ů�����֣����Ϊ�գ���ô������Ů
     */
    public static String getRandomName(boolean... isMale) {
        char[] first = "�������������������������������θ���֣л������������˲��������ﶭԬ���ڽ������Ҷ����κ��������Ҧ¬������̷½������ʯ�μ���Τ������������������Ѧ�ƶ��׺���ʷ����ع�ë�¹�����Ǯ�������Ī������".toCharArray(), last = null;
        String MSeed = "��˴ة�����ʳ�������΁��������٩٭��ٹ��ٶ�ނ�����������ɮ���忡ΰ����մ�ǰ����ۿ�ƿ���������ʿ׳���ؿ����������ʵ�����ž��獃����Ѳ˧��ս�ų�����ի���˕������B��A�S衬d���k���`�Z�`����봣ʸʯ��ɰ�Vʾ������������¿�����������ϴ�����������ӽܰ�����������ֶ�����������﬷������ظ�������Լݽ���ں��Ľ�����Ὠ��ǿ���������ʿ����н�������Х��ľ��ɭ��幺����߿�κ����ӥاƹ�����ı��ܱ��ܷ�ɽ���������������c��ʤ���������ΜB���������읻����尲���������������ϲ��������𺫸�����Ƶ������������ڴ�����������׿�܆�չ�۳ڏ|�������ɵ�־��˼��ͦ���@�F껕f���ɕ����ʶε�̩�����������ѡ���¾�������������λ�īŷ��������";
        String FSeed = "������������������ܿ�ƺ������˿ɼ���Ӱ��֦˼�����Ӣ���������Ⱦ���������֥��Ƽ�����ҷ���ʴ����������������÷���������滷ѩ�ٰ���ϼ����ݺ�����𷲼Ѽ�����Ҷ�������������ɺɯ������ٻ�������ӱ¶������������Ǻɵ���ü������ޱݼ���Է�ܰ�������԰��ӽ�������ع���ѱ�ˬ������ϣ����Ʈ�����";

        if(isMale.length == 0) {
            last = (MSeed + FSeed).toCharArray();
        } else if(isMale[0]) {
            last = MSeed.toCharArray();
        } else {
            last = FSeed.toCharArray();
        }

        Random r = new Random();
        StringBuilder name = new StringBuilder(String.valueOf(first[r.nextInt(first.length)]));
        name.append(last[r.nextInt(last.length)]);
        name.append(last[r.nextInt(last.length)]);

        return String.valueOf(name);
    }


    /**
     * �������ɲ�������
     *
     *  ���ȣ��������ݿ�
     *  -- create database pagination
     *  ��Σ�������Ҫ�Ĳ��Ա�
     *  -- create table person(id int primary key, name varchar(20) not null, weixin varchar(20));
     *
     *  Ȼ��ִ�����淽������������:
     *
     */
    public static void initData() {
        String SQL = "insert into person values (?, ?, ?)";
        try(Connection conn = getConn();
            PreparedStatement ps = conn.prepareStatement(SQL)) {
            for(int i = 0; i < 300; i++) {
                ps.setInt(1, i);
                ps.setString(2, getRandomName());
                ps.setString(3, String.valueOf(System.currentTimeMillis()));
                ps.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("��ʼ����ɡ�");
    }


    /**
     * ��һ����ͨ��ѯ����װΪһ����ҳ��ѯ���
     *
     * ����������� sqlserver2008
     *
     * @param sql ԭ�������
     * @param orderBy �������
     * @param offset ����һ�п�ʼ
     * @param size ��ȡ������
     * @return ��װ������䣬����˷�ҳ����
     *
     */
    public static String pageLimitSQL(String sql, String orderBy, int offset, int size) {
        String sql_temp = "select * from ( select *, ROW_NUMBER() over (order by %s) _rn from ( %s ) as __o ) as __p where _rn >= %d and _rn < %d";
        return String.format(sql_temp, orderBy, sql, offset, offset + size);
    }



    public static void main(String[] args) throws Exception {
        System.out.println(getRandomName(false));
        initData();
    }
}
