package com.nf.howdoyoudo.pagination;


/**
 * 首先，一句至理名言：搜索引擎是你们的好朋友！
 * 其次，有一句至理名言：与其坐以待毙，不如谷歌百度。
 *
 *
 * 正题：
 *
 * 写测试有助于帮助我们理清思路，消除错误
 * 所以要尽量养成随手写测试方法的习惯
 *
 *
 * 可以用第三方的一些框架，如 junit，也可以简单使用 java 的 assert 断言
 *
 * assert 并不是默认开启的，如果想生效，需要在 java 的参数里增加 -ea，即 -enableassertions
 *
 */
public class PaginatorTest {

    /**
     * 根据总记录数和每页记录数，计算总页数
     */
    public static int getPageCount(int rowCount, int pageSize) {
        // return (rowCount - 1)/pageSize + 1;
        // return (rowCount + pageSize - 1) / pageSize;
        return rowCount/pageSize + (rowCount%pageSize == 0 ? 0 : 1);
    }

    /**
     * 示例，为 getPageCount 写对应的测试方法。
     *
     * 思路为：
     * 【假设每页显示 4 条，那么我们有下面结论】：
     *
     * 记录数 | 总页数
     *    1     1
     *    2     1
     *    4     1
     *    5     2
     *    6     2
     *    8     2
     *    9     3
     *
     *  那只要 getPageCount() 方法能让上面的情况都满足，我们就可以认为我们写对了。
     *
     *  所以，如果思路不明确，或者不清楚自己有没有考虑周全，那先把测试方法写出来。
     *
     *  我们实现 getPageCount() 的目的，就是让下面的测试方法能通过执行。
     *
     */
    public static void testGetPageCount() {
        assert getPageCount(1, 4) == 1;
        assert getPageCount(2, 4) == 1;
        assert getPageCount(4, 4) == 1;

        assert getPageCount(6, 4) == 2;
        assert getPageCount(5, 4) == 2;
        assert getPageCount(8, 4) == 2;

        assert getPageCount(9, 4) == 3;
    }





    public static void testSetLinks(int current) {
        Paginator paginator = new Paginator(current, 9, 5);
        paginator.setRowCount(100);
        System.out.println(paginator.getLinks());
    }



    public static void main(String[] args) {
        //for(int i=3; i<12; i++) {
        //    testSetLinks(i);
        //}
        testGetPageCount();
    }
}
