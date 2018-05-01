<%--
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
</head>
<body>

<%
    String week = (String) request.getAttribute("week");
    System.out.println(week);
    String info = (String) request.getAttribute("info");
    String url = (String) request.getAttribute("url");
    String login_info = (String) request.getAttribute("login_info");

%>

<div class="jumbotron text-center">
    <% int lastWeek = Integer.parseInt(week) -1;
        if (lastWeek > 0) {%>
    <a href="/TuringCalendar?last=<%=lastWeek%>">last</a>
    <%
        }
    %>

    <a href="/TuringCalendar">now</a>
    <% int nextWeek = Integer.parseInt(week) + 1;
        if (nextWeek < 62) {%>
    <a href="/TuringCalendar?next=<%=nextWeek%>">next</a>
    <%
        }
    %>



    <p style="text-align: right"><a><%=info%></a></p>
    <p style="text-align: right"><a href=<%=url%>><%=login_info%></a></p>

    <div>
        <div><img src="/static/TuringCalendar/TuringCalendar-<%=week%>.jpg" width="60%" ></div>
    </div>

</div>
</body>
</html>
