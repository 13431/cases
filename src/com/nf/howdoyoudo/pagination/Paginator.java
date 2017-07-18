package com.nf.howdoyoudo.pagination;

import java.util.ArrayList;
import java.util.List;


/**
 * ��װ��ҳ����
 *
 * ʹ�÷�����
 * 1. ��ȡ�û���������ʹ����Щ�������� {@link Paginator} ����
 * 2. ��ѯ���ݿ⣬�õ���¼���������浽 {@link Paginator} ����
 * 3. ��ѯ���ݿ⣬�õ���ҳ���ݣ����浽 {@link Paginator} ���󡣿���ͨ�� {@link Paginator#getOffset()} �õ����ݵĿ�ʼ��š�
 * 4. ���� {@link Paginator} �� request �� session �У�����ҳ����Ⱦ��
 * 5. ʹ�ñ���� {@link Paginator} ������Ⱦҳ�棬����ʹ�� {@link Paginator#outputPageNav(String, String, boolean, boolean)} ֱ�����ҳ�뵼��
 *
 * <code>
 *
 *     // in the servlet
 *     Paginator pager = new Paginator(��ǰҳ, ÿҳ��ʾ��Ŀ, ������ҳ��);
 *
 *     pager.setRowCount(dao.count());
 *     pager.setData(dao.findAll(page.getOffset(), page.getSize());
 *
 *     request.addAttribute("pager", pager);
 *
 *
 *     // in the jsp
 *     <style>
 *         #pagination {}
 *     </style>
 *     <table>
 *         <%-- ����չʾ --%>
 *     </table>
 *     <nav>
 *         <%-- ҳ�뵼���� --%>
 *         ${page.outputPageNav("/url", "pageno", true, true)}
 *     </nav>
 *
 * </code>
 * @param <T>
 */
public class Paginator<T> {

    // ���û�����ȡ�Ĳ���
    private int current = 1;   // ��ǰҳ��
    private int pageSize = 10; // ÿҳ��Ŀ
    private int linkSize = 5;  // ������ҳ����Ŀ

    // �����ݿ��ѯ��������
    private int rowCount;      // ��ʾ�ܹ��ж�������
    private List<T> data = new ArrayList<>();  // ��ȡ��������

    // ������������������
    private int pageCount;     // �ܹ��ж���ҳ
    private List<Integer> links = new ArrayList<>();  // ������Ҫ��ʾ��ҳ��



    /* Constructors */
    public Paginator() {
    }

    public Paginator(int current, int pageSize, int linkSize) {
        this.current = current;
        this.pageSize = pageSize;
        this.linkSize = linkSize;
    }



    /* Accessors */
    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getLinkSize() {
        return linkSize;
    }

    public void setLinkSize(int linkSize) {
        this.linkSize = linkSize;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
        this.pageCount = (rowCount + pageSize - 1) / pageSize;
        calcLinks();
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getOffset() {
        return (this.current - 1) * this.pageSize + 1;
    }

    public int getPageCount() {
        return pageCount;
    }

    public List<Integer> getLinks() {
        return links;
    }

    /**
     * ����/ˢ�µ�����Ҫ��ʾ��ҳ��
     */
    public void calcLinks() {
        // ���������������㣺
        // 1. ��ǰҳ current
        // 2. ��ҳ�� pageCount
        // 3. ��ʾ��Ŀ linkSize
        int halfLinkSize = this.linkSize/2;
        int startPage = Math.max(this.current - halfLinkSize, 1);
        int endPage = Math.min(this.current + halfLinkSize, this.pageCount);

        if(endPage - startPage < this.linkSize - 1) {
            if(startPage == 1) {
                endPage = Math.min(startPage + this.linkSize - 1, this.pageCount);
            } else {
                startPage = Math.max(endPage - this.linkSize + 1, 1);
            }
        }
        for(int i = startPage; i <= endPage; i++) {
            this.links.add(i);
        }
    }

    /**
     * ����������� html
     *
     * �򵥷�װ���ɸ��������Լ�����
     *
     * @param url ����·��
     * @param pagenoParam ��ʾ������ҳ����ֶ�����
     * @param isFirstLast �Ƿ���ʾ��ҳĩҳ
     * @param isPrevNext  �Ƿ���ʾ��ҳ��ҳ
     * @return ��ҳ�� html ���
     */
    public String outputPageNav(String url, String pagenoParam, boolean isFirstLast, boolean isPrevNext) {

        String firstLabel = "��ҳ", lastLabel = "ĩҳ", prevLabel = "?", nextLabel = "?";

        String normalLink = "  <li><a href='" + url + "?" + pagenoParam + "=%s'>%s</a></li>\n";
        String currentLink = "  <li><a href='#' class='current'>%s</a></li>\n";
        String readonlyLink = "  <li><a href='#' class='ro'>%s</a></li>\n";

        StringBuilder output = new StringBuilder("<ul id='pagination'>\n");

        // ��ҳ
        if(isFirstLast) {
            if(current == 1) {
                output.append(String.format(readonlyLink, firstLabel));
            } else {
                output.append(String.format(normalLink, 1, firstLabel));
            }
        }

        // ��һҳ
        if(isPrevNext) {
            if(current < 2) {
                output.append(String.format(readonlyLink, prevLabel));
            } else {
                output.append(String.format(normalLink, current - 1, prevLabel));
            }
        }

        // �� x ҳ
        for(Integer i : links) {
            if(current == i) {
                output.append(String.format(currentLink, i));
            } else {
                output.append(String.format(normalLink, i, i));
            }
        }

        // ��һҳ
        if(isPrevNext) {
            if(current >= pageCount) {
                output.append(String.format(readonlyLink, nextLabel));
            } else {
                output.append(String.format(normalLink, current + 1, nextLabel));
            }
        }

        // βҳ
        if(isFirstLast) {
            if(current == pageCount) {
                output.append(String.format(readonlyLink, lastLabel));
            } else {
                output.append(String.format(normalLink, pageCount, lastLabel));
            }
        }

        return String.valueOf(output.append("</ul>"));
    }

}
