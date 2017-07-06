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

        .upload-wrapper {
            position: relative;
            text-align: center;
            font-size: 1.3em;
            background: dodgerblue;
            color: white;
            padding: 8px 15px;
            margin-bottom: 15px;
            width: 200px;
            border-radius: 5px;
            cursor: hand;
        }

        input[type=text] {
            margin-top: 15px;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>

<div>

    <form action="/upload" method="post" enctype="multipart/form-data" class="upload-form">
        <label class="upload-wrapper">
            请点击选择文件
            <input type="file" id="meinv" name="meinv" style="position: absolute; opacity: 0;" />
        </label>

        <img id="preview" src="" style="width: 100%; max-height: 300px;" />

    <%--<input type="file" name="meinv" style="margin-bottom: 10px;" multiple />--%>
        <input type="text" name="describe" placeholder="请输入文件的描述~" />
        <input type="submit" value="上传美女图片，别忘记点赞哦" />
    </form>

</div>
</body>


<script src="../../assets/javascript/jquery.js"></script>
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
