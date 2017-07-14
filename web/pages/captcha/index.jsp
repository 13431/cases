<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>验证码示例</title>

    <style>

        fieldset {
            max-width: 300px;
            margin: 20px auto;
            padding: 20px 40px;
            border: 1px solid darkgoldenrod;
            border-radius: 5px;
        }

        legend {
            font-weight: bold;
            padding: 5px 10px;
        }

        label {
            display: flex;
            margin-bottom: 1em;
        }

        label span {
            text-align: right;
            margin-right: 10px;
            width: 100px;
        }
        label input {
            flex: 1;
            height: 30px;
            border: 1px solid #ddd;
        }

        label img {
            height: 30px;
        }

        fieldset input[type=submit] {
            border: 0;
            padding: 10px 20px;
            font-size: 1.2em;
            border-radius: 5px;
            color: darkgoldenrod;
            float: right;
            cursor: hand;
        }
        fieldset input[type=submit]:hover {
            background: #ccc;
        }

    </style>
</head>

<body>

<form method="post">

    <fieldset>

        <legend>用户登录</legend>

        <label>
            <span>用户名</span>
            <input name="yourusername" />
        </label>

        <label>
            <span>密码</span>
            <input name="yourpassword" type="password" />
        </label>

        <label>
            <span><img id="verifycodeimg" title="验证码" src="${pageContext.request.contextPath}/verifycode" /></span>
            <input id="verifycode" name="verifycode" placeholder="请输入验证码" />
        </label>

        <div>
            <input type="submit" value="登录" />
        </div>
    </fieldset>

</form>

<script>
    document.getElementById("verifycodeimg").onclick = function () {
        document.getElementById("verifycode").valueOf = "";
        this.src = this.src.split("?")[0] + "?" + Math.random();
    }
</script>`


</body>
</html>
