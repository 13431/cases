package com.nf.howdoyoudo.pagination.mssql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;


public class DBHelper {


    /**
     * 通过 JDBC 获取数据库连接
     *
     * 如果不知道 SQLServer 端口号，可以通过下面语句查看：
     * -- exec sys.sp_readerrorlog 0, 1, 'listening'
     *
     * @return SqlServer2008 的数据库连接
     */
    public static Connection getConn () throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=pagination", "sa", "123456");
    }


    /**
     * 获取一个随机的中文名字
     * @param isMale 男生名字还女生名字，如果为空，那么不分男女
     */
    public static String getRandomName(boolean... isMale) {
        char[] first = "王李张刘陈杨黄赵吴周徐孙马朱胡郭何高林郑谢罗梁宋唐许韩冯邓曹彭曾萧田董袁潘于蒋蔡余杜叶程苏魏吕丁任沈姚卢姜崔钟谭陆汪范金石廖贾夏韦付方白邹孟熊秦邱江尹薛闫段雷侯龙史陶黎贺顾毛郝龚邵万钱严覃武戴莫孔向汤".toCharArray(), last = null;
        String MSeed = "世舜丞主产仁仇仓仕仞任伋众伸佐佺侃侪促俟信俣修倝倡倧偿储僖僧僳儒俊伟列则刚创前剑助劭势勘参叔吏嗣士壮孺守宽宾宋宗宙宣实宰尊峙峻崇崈川州巡帅庚战才承拯操斋昌晁暠曹曾珺玮珹琒琛琩琮琸瑎玚璟璥瑜生畴矗矢石磊砂碫示社祖祚祥禅稹穆竣竦综缜绪舱舷船蚩襦轼辑轩子杰榜碧葆莱蒲天乐东钢铎铖铠铸铿锋镇键镰馗旭骏骢骥驹驾骄诚诤赐慕端征坚建弓强彦御悍擎攀旷昂晷健冀凯劻啸柴木林森朴骞寒函高魁魏鲛鲲鹰丕乒候冕勰备宪宾密封山峰弼彪彭旁日明昪昴胜汉涵汗浩涛淏清澜浦澉澎澔濮濯瀚瀛灏沧虚豪豹辅辈迈邶合部阔雄霆震韩俯颁颇频颔风飒飙飚马亮仑仝代儋利力劼勒卓哲喆展帝弛弢弩彰征律德志忠思振挺掣旲旻昊昮晋晟晸朕朗段殿泰滕炅炜煜煊炎选玄勇君稼黎利贤谊金鑫辉墨欧有友闻问";
        String FSeed = "筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊勤珍贞莉兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥";

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
     * 批量生成测试数据
     *
     *  首先，创建数据库
     *  -- create database pagination
     *  其次，创建需要的测试表
     *  -- create table person(id int primary key, name varchar(20) not null, weixin varchar(20));
     *
     *  然后，执行下面方法，插入数据:
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

        System.out.println("初始化完成。");
    }


    /**
     * 将一个普通查询语句包装为一个分页查询语句
     *
     * 这个可以用于 sqlserver2008
     *
     * @param sql 原来的语句
     * @param orderBy 排序的列
     * @param offset 从哪一行开始
     * @param size 获取多少条
     * @return 包装过的语句，添加了分页参数
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
