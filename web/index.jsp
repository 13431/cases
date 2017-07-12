<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% request.setAttribute("ss", request.getContextPath()); %>
<html>
<head>
    <title>示例</title>
</head>
<body>
<div>
    <h2>List of Contents:</h2>
    <ul>
        <li><a href="${ss}/pages/fileupload/">文件上传</a></li>
        <li><a href="${ss}/pages/captcha">验证码</a></li>
        <li><a href="${ss}/paging?pageno=1">分页导航</a></li>
    </ul>
</div>
</body>
</html>
