<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>

    <style>
        .upload-form {
            display: flex;
            flex-flow: column;
            max-width: 500px;
            margin: 10px auto;
        }
    </style>
</head>
<body>

<div>

    <form action="/upload" method="post" enctype="multipart/form-data" class="upload-form">
        <label style="position: relative; background: lightgoldenrodyellow; border: 2px solid orange; width: 200px; border-radius: 5px; cursor: hand;">
            <p style="padding: 5px 8px; ">请点击选择文件</p>
            <input type="file" id="meinv" name="meinv" style="margin-bottom: 10px; position: absolute; top: 0; left: 0; opacity: 0;" />
        </label>
        <%--<input type="file" name="meinv" style="margin-bottom: 10px;" multiple />--%>
        <input type="text" name="describe" />
        <input type="submit" value="上传美女图片，别忘记点赞哦" />

        <img id="preview" src="" style="width: 100%; max-height: 300px;" />
    </form>

</div>
</body>


<script src="assets/javascript/jquery.js"></script>
<script>
    $("#meinv").change(function () {
        var meinvFile = this.files[0];

        var reader = new FileReader();
        reader.onload = function () {
            $("#preview").attr("src", this.result);
        };
        reader.readAsDataURL(meinvFile);


        $("#preview").attr("src", $("#meinv").val());
        console.log(meinvFile);
    });

</script>
</html>
