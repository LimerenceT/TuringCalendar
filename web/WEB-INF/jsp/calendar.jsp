<%--
  Created by IntelliJ IDEA.
  User: ql
  Date: 2018/4/2
  Time: 16:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8" language="java" %>
<html>
<head>
    <title>MyCalendar</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>

<%
    String week = (String) request.getAttribute("week");
    String info = (String) request.getAttribute("info");
    String url = (String) request.getAttribute("url");
    String login_info = (String) request.getAttribute("login_info");
    String up = (String) request.getAttribute("up");
    String down = (String) request.getAttribute("down");
%>

<div class="jumbotron text-center">
    <%if (true) {%>
    <button type="button" class="btn btn-default btn-lg" onclick=window.location.href="up.do?week=<%=week%>">
        <span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>(<%=up%>)
    </button>
    <button type="button" class="btn btn-default btn-lg" onclick=window.location.href="down.do?week=<%=week%>">
        <span class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span>(<%=down%>)
    </button>
    <%}%>

    <% int lastWeek = Integer.parseInt(week) -1;
    if (lastWeek > 0) {%>
    <button type="button" class="btn btn-default btn-lg" onclick=window.location.href="last.do?week=<%=lastWeek%>">
        <span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>
    </button>
    <%}%>

    <button type="button" class="btn btn-default btn-lg" onclick=window.location.href="now.do">
        <span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span>

    </button>
    <% int nextWeek = Integer.parseInt(week) + 1;
        if (nextWeek < 62) {%>
    <button type="button" class="btn btn-default btn-lg" onclick=window.location.href="next.do?week=<%=nextWeek%>">
        <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
    </button>
    <%}%>



    <p style="text-align: right"><a><%=info%></a></p>
    <p style="text-align: right"><a href=<%=url%>><%=login_info%></a></p>

    <div>
        <div><img src="/static/TuringCalendar/TuringCalendar-<%=week%>.jpg" width="60%" ></div>
    </div>

</div>
</body>
</html>
