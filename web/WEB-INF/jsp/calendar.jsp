<%@ page import="turing.Model.User" %><%--
  Created by IntelliJ IDEA.
  User: ql
  Date: 2018/4/2
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" language="java" %>

<head>
    <title>calendar</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>

<%
    String week = (String) request.getAttribute("week");
    HttpSession httpSession = request.getSession();
    User user  = (User)httpSession.getAttribute("user");
    String info;
    String url;
    String login_info;
    if (user != null) {
        info = "欢迎回来," + user.getUsername();
        url = "logout.do";
        login_info = "退出登录";
    }
    else {
        info = "未登录";
        url = "login.jsp";
        login_info = "登录";
    }

%>

<div class="jumbotron text-center">

    <p style="text-align: right"><a><%=info%></a></p>
    <p style="text-align: right"><a href=<%=url%>><%=login_info%></a></p>

    <div>

        <img src="/static/TuringCalendar/TuringCalendar-<%=week%>.jpg" width="50%" >

    </div>
</div>
</body>
</html>
