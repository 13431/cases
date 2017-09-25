package com.nf.hibernate.assoc.many2one_twoway;

import com.nf.hibernate.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MOTTest {


    @Test
    public void testGetAuthor() {
        // 查询作者
        Author author = session.get(Author.class, 1l);
        // 获取书籍
        List<Book> books = author.getBooks();
        // 打印信息
        for (Book book : books) {
            System.out.println("作者：" + author.getName() + "   作品：《" + book.getName() + "》");
        }
    }

    @Test
    public void testQueryBook() {
        Book book1 = (Book) session.createQuery("select b from Book b where b.id = :id")
                .setParameter("id", 2l)
                .uniqueResult();
        System.out.println(book1);
        System.out.println(book1.getAuthor().getName());
    }

    @Test
    public void testGetBook () {
//        initDataForLuxun();
//        session.flush();

        System.out.println("==============");
        Book book1 = session.get(Book.class, 1l);
        System.out.println(book1);

        System.out.println(">>>>>>>>>>>>>>");
        System.out.println(book1.getAuthor().getName());
    }


    @Test
    public void initDataForMoyan () {

        Book book1 = new Book("檀香刑", 22f, null);
        Book book2 = new Book("丰乳肥臀", 23.2f, null);

        Author moyan = new Author("莫言", "120");

        // 下面一段没卵用
        List<Book> books = moyan.getBooks();
        books.add(book1);
        books.add(book2);

        // 这样才可以
        book1.setAuthor(moyan);
        book2.setAuthor(moyan);

        // 保存的时候，一定要先保存一的一方，否则，会有冗余的 update 语句
        session.save(book1);
        session.save(book2);

        session.save(moyan);
    }

    @Test
    public void initDataForLuxun() {

        Author luxun = new Author();
        luxun.setName("鲁迅");
        luxun.setPhone("1111111111110");

        session.save(luxun);

        Book book1 = new Book("呐喊", 12f, luxun);
        Book book2 = new Book("彷徨", 211f, luxun);

        session.save(book1);
        session.save(book2);

        session.save(new Book("故事新编", 22f, luxun));
        session.save(new Book("野草集", 43f, luxun));

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
