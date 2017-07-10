<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% request.setAttribute("ss", request.getContextPath()); %>

<html>
<head>
    <title>文件上传示例</title>

    <style>

        fieldset {
            border: 0;
            width: 300px;
            margin: 20px auto;
        }

        fieldset img {
            width: 100%;
            max-height: 200px;
            margin-bottom: 2em;
        }

        .btn-group {
            display: flex;
            justify-content: space-between;
            text-align: center;
        }

        .btn-item {
            border: 0;
            flex: 1;
            font-size: 1.2em;
            padding: 8px 15px;
            border-radius: 5px;
            background: #333;
            color: goldenrod;
            cursor: hand;
        }

        .btn-item:first-child {
            margin-right: 1.5em;
        }

        .btn-item:hover {
            background: #000;
        }

    </style>

</head>
<body>

<div>
    <form action="${ss}/upload" method="post" enctype="multipart/form-data">
        <fieldset>
            <div>
                <img id="preview" src="" title="图片"/>
            </div>

            <div class="btn-group">
                <label class="btn-item">选择图片
                    <input type="file" id="pic" name="pic" required="required" style="display: none;"/>
                </label>

                <input class="btn-item" type="submit" value="上传图片"/>
            </div>

        </fieldset>
    </form>

</div>
</body>

<script>
    document.querySelector("#pic").addEventListener("change", function () {
        document.querySelector("#preview").src = URL.createObjectURL(this.files[0]);
    });
</script>
</html>
