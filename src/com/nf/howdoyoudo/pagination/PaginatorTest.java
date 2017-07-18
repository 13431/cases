package com.nf.howdoyoudo.pagination;


/**
 * ���ȣ�һ���������ԣ��������������ǵĺ����ѣ�
 * ��Σ���һ���������ԣ��������Դ��У�����ȸ�ٶȡ�
 *
 *
 * ���⣺
 *
 * д���������ڰ�����������˼·����������
 * ����Ҫ������������д���Է�����ϰ��
 *
 *
 * �����õ�������һЩ��ܣ��� junit��Ҳ���Լ�ʹ�� java �� assert ����
 *
 * assert ������Ĭ�Ͽ����ģ��������Ч����Ҫ�� java �Ĳ��������� -ea���� -enableassertions
 *
 */
public class PaginatorTest {

    /**
     * �����ܼ�¼����ÿҳ��¼����������ҳ��
     */
    public static int getPageCount(int rowCount, int pageSize) {
        // return (rowCount - 1)/pageSize + 1;
        // return (rowCount + pageSize - 1) / pageSize;
        return rowCount/pageSize + (rowCount%pageSize == 0 ? 0 : 1);
    }

    /**
     * ʾ����Ϊ getPageCount д��Ӧ�Ĳ��Է�����
     *
     * ˼·Ϊ��
     * ������ÿҳ��ʾ 4 ������ô������������ۡ���
     *
     * ��¼�� | ��ҳ��
     *    1     1
     *    2     1
     *    4     1
     *    5     2
     *    6     2
     *    8     2
     *    9     3
     *
     *  ��ֻҪ getPageCount() ���������������������㣬���ǾͿ�����Ϊ����д���ˡ�
     *
     *  ���ԣ����˼·����ȷ�����߲�����Լ���û�п�����ȫ�����ȰѲ��Է���д������
     *
     *  ����ʵ�� getPageCount() ��Ŀ�ģ�����������Ĳ��Է�����ͨ��ִ�С�
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
