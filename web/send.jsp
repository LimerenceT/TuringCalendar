<%@ page  %><%--
  Created by IntelliJ IDEA.
  User: ql
  Date: 2018/4/17
  Time: 18:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>正在跳转</title>
</head>
<%--<%--%>
    <%--String info = (String) request.getAttribute("message");--%>

<%--%>--%>
<meta http-equiv='refresh' content='5;url=/TuringCalendar'>
<script type='text/javascript'>
    var i=5;
    function getTime(){
        document.getElementById('num').innerHTML="<font color='red'>"+i+"</font>";
        i-=1;
        var x=setTimeout('getTime()',1000)
        if(i<=0){
            clearTimeout(x);
        }
    }
    window.onload=getTime;
</script>
<body>
<p style="text-align: center">注册成功，正在跳转</p>
</body>
</html>
