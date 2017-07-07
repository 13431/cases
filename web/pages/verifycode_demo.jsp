<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录验证码测试</title>
</head>

<body>

<form style="display: flex; width: 300px; margin: 20px auto;">
    <img src="/verifycode" id="verifycode" title="验证码" style="height: 50px; width: 200px;" />
    <input name="verifycode" placeholder="请输入验证码" />

    <input type="submit" value="登录" />
</form>

<script>
    document.getElementById("verifycode").onclick=function () {
        this.src = "/verifycode?" + Math.random();
    }
</script>


</body>
</html>
