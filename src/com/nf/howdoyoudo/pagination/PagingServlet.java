package com.nf.howdoyoudo.pagination;


import com.nf.howdoyoudo.pagination.mssql.Person;
import com.nf.howdoyoudo.pagination.mssql.PersonDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/paging")
public class PagingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.��ȡ�û����ݹ����Ĳ���
        String pagenoStr = req.getParameter("pageno");      // ��ȡҳ��
        int pageno = pagenoStr == null ? 1 : Integer.parseInt(pagenoStr);  // ���ҳ��Ϊ�գ������ʼֵ 1.

        // 2. ������ҳ���󣬽��û��������ݸ���
        Paginator<Person> paginator = new Paginator<>(pageno, 9, 7);

        // 3. ��ѯ���ݿ⣬�ܹ�������
        PersonDAO personDAO = new PersonDAO();
        paginator.setRowCount(personDAO.getPersonCount());

        // 4. ��ѯ���ݿ⣬����Ҫ������
        paginator.setData(personDAO.findPersonsInRange(paginator.getOffset(), paginator.getPageSize()));

        // 5. �ŵ� request ����
        req.setAttribute("pager", paginator);

        // 6. ��ת��ҳ�룬��Ⱦ����
        req.getRequestDispatcher("pages/pagination/index.jsp").forward(req, resp);

    }


}
