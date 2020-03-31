<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <script type="text/javascript" src="js/jquery.min.js" ></script>
    <script>
        $(function(){
            $("#btn").click(function(){ //函数在$(function(){})内
                var uu={"user":{"username":"刘书源","password":"123456","age":90},"newAge":77};
               $.ajax({
                   url:"user/testAjax",
                   contentType:"application/json;charset=UTF-8",
                   data:JSON.stringify(uu),
                   dataType:"json",
                   type:"post",
                   success:function (data) {
                       $("#username").val(data.username);
                       $("#password").val(data.password);
                       $("#age").val(data.age);
                      alert(data.username);
                      alert(data.password);
                      alert(data.age);
                   }
               })
            });
        });
    </script>

</head>
<body>
<p>测试测试测试测试</p><br/>

<button id="btn">发送ajax请求</button><br/>
<a href="user/tx">测试</a><br/>
<input id="username" name="username" type="text" /><br/>
<input name="password" id="password" type="text" /><br/>
<input name="age" id="age" type="text" /><br/>
</body>
</html>
