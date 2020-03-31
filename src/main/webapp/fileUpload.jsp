<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2019/11/21
  Time: 18:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <title>Title</title>
    <script>
        function ajaxFunction() {
            var xmlHttp;
            try {
                xmlHttp=new XMLHttpRequest();
            }catch (e) {
                try {
                    xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
                }catch (e) {
                    try {
                        xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
                    }catch (e) {
                    }
                }
            }
            return xmlHttp;
        }
    </script>
    <script>
        function checkName() {
            var name= document.getElementById("name").value;
            alert("写的名字："+name);
            var request=ajaxFunction();
            request.open("POST","user/Ajax1",true);
            request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
            request.send("name="+name);
            request.onreadystatechange=function () {
                if(request.readyState==4 && request.status==200){
                    alert(request.responseText);
                }
            }
        }
    </script>
</head>
<body>
<form action="user/upload" method="post" enctype="multipart/form-data">
<input name="upload" type="file" /><br/>
    <input type="submit" value="上传文件"/>
</form>
<br/>
<p>************************************************************</p><br/>
<form action="user/atWillUpload" method="post" enctype="multipart/form-data">
    <input name="upload" type="file" /><br/>
    <input type="submit" value="指定任意位置上传文件"/>
</form>
<br/>
<p>************************************************************</p><br/>
<form action="springMvcFileUpload" method="post" enctype="multipart/form-data">
    <input type="text" name="username" /><br/>
    <input type="text" name="password" /><br/>
    <input type="text" name="age" /><br/>
    <input type="text" name="sex" /><br/>
    <input name="upload" type="file" /><br/>

    <input type="submit" value="SpringMvc方式上传文件"/>
</form>
<p>************************************************************</p><br/>
<form>
     用户名： <input type="text" id="name" onblur="checkName()"/> <br/>
</form>
<p>************************************************************</p><br/>
</body>
</html>
