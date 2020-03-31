<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js" ></script>
    <script>
        window.onload=function(){

             $("#download").click(function () {
                 alert("ajax@@@@@@@");
                 $.ajax({
                     url:"${pageContext.request.contextPath}/user/doDownFile2",//目标地址
                     contentType:"application/json;charset=UTF-8",
                     data:'{"filename":"${pic}"}',
                     type:"post",
                     dataType:"json",
                     success: function () {
                     }
                 });
             })
            <%--var download=document.getElementById("download");--%>
            <%--download.onclick=function() {--%>
            <%--    alert("11111111111111111111");--%>
            <%--    $.ajax({--%>
            <%--        url:"${pageContext.request.contextPath}/user/doDownFile2",//目标地址--%>
            <%--        contentType:"application/json;charset=UTF-8",--%>
            <%--        data:'{"filename":"${pic}"}',--%>
            <%--        type:"post",--%>
            <%--        dataType:"json",--%>
            <%--        success: function () {--%>
            <%--        }--%>
            <%--    });--%>
            <%--}--%>
        }

    </script>
</head>
<body>
<p>文件上传成功</p>
<input type="text" value="文件名:${pic}"/>
<%--<input type="button" class="input1" οnclick="doDownFile('${pic}')" value=" 下载"/>--%>
<input type="button" id="download" value=" 下载"/>
<br/><br/>
<form action="doDownFile" method="post">
    <input type="text" name="filename" value="${pic}"/>

    <input type="submit"  value=" 下载2"/>
</form>

<form action="doDownFile" method="post">
    <input type="text" name="filename"/>

    <input type="submit"  value="绝对路径"/>
</form>
<a href="/doDownFile">4444444444444</a>
</body>
</html>
