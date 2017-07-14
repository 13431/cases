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
        // 1.获取用户传递过来的参数
        String pagenoStr = req.getParameter("pageno");      // 获取页码
        int pageno = pagenoStr == null ? 1 : Integer.parseInt(pagenoStr);  // 如果页码为空，赋予初始值 1.

        // 2. 创建分页对象，将用户参数传递给它
        Paginator<Person> paginator = new Paginator<>(pageno, 9, 7);

        // 3. 查询数据库，总共多少条
        PersonDAO personDAO = new PersonDAO();
        paginator.setRowCount(personDAO.getPersonCount());

        // 4. 查询数据库，所需要的数据
        paginator.setData(personDAO.findPersonsInRange(paginator.getOffset(), paginator.getPageSize()));

        // 5. 放到 request 里面
        req.setAttribute("pager", paginator);

        // 6. 跳转到页码，渲染数据
        req.getRequestDispatcher("pages/pagination/index.jsp").forward(req, resp);

    }


}
