<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>分页示例</title>
    <style>
        #container {
            width: 600px;
            margin: 20px auto;
        }

        /* 表格样式 */
        table {
            margin-top: 1em;
            width: 100%;
        }
        table, tr, th, td {
            border: 1px solid darkgoldenrod;
            border-collapse: collapse;
        }
        tr:nth-child(odd) {
            background: palegoldenrod;
        }
        td {
            padding: 5px 8px;
        }

        /* 定制分页导航样式 */
        nav {
            text-align: center;
        }
        nav ul {
            display: inline-block;
        }
        nav ul li {
            display: inline;
        }
        nav ul a {
            font-size: 1.2em;
            padding: 8px 10px;
            float: left;
            text-decoration: none;
        }
        nav ul a:hover {
            background: #ddd;
        }
        nav .current {
            background: goldenrod;
        }


    </style>
</head>
<body>

<div id="container">

    <section>
        <%--头部导航栏--%>
        <header>
            总共有 ${pager.pageCount} 页，当前是 ${pager.current} 页。
        </header>

        <%--数据展示区--%>
        <table>
            <tr>
                <th>ID</th>
                <th>名字</th>
                <th>微信</th>
            </tr>

            <c:forEach items="${pager.data}" var="p">
                <tr>
                    <td>${p.id}</td>
                    <td>${p.name}</td>
                    <td>${p.weixin}</td>
                </tr>
            </c:forEach>
        </table>

    </section>

    <nav>
        <%--分页导航条--%>
        ${pager.outputPageNav("/paging", "pageno", true, true)}
    </nav>


</div>

</body>
</html>
