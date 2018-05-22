<%@ page import="turing.Model.User" %><%--
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
    <link rel="stylesheet" type="text/css" href="../static/css/dropdown.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">

</head>
<body>

<%
    String week = (String) request.getAttribute("week");
    String info = (String) request.getAttribute("info");
    String url = (String) request.getAttribute("url");
    String login_info = (String) request.getAttribute("login_info");
    String up = (String) request.getAttribute("up");
    String down = (String) request.getAttribute("down");
    boolean upState = (boolean) request.getAttribute("upState");
    boolean downState = (boolean) request.getAttribute("downState");
%>
<script>
    function isLogin(upOrDown) {
        if ("<%=login_info%>"==="登录"){
            alert("请先登录")
        }
        else if (<%=upState%>){
            alert("你已经点过赞，暂时不能改变")
        }
        else if (<%=downState%>){
            alert("你已经踩过了，暂时不能改变")
        }
        else {
            <%
                String name = null;
                if (info.length()>5) {
                     name = info.substring(5);
                }
            %>
            return window.location.href=upOrDown+".do?week=<%=week%>&name=<%=name%>"
        }
    }
</script>
<div class="jumbotron text-center">
    <%
        if (upState) {
    %>
    <button type="button" class="btn btn-default btn-lg" onclick="isLogin('up')">
        <span class="fas fa-thumbs-up" aria-hidden="true" ></span>(<%=up%>)
    </button>

    <%
        }else {

    %>
    <button type="button" class="btn btn-default btn-lg" onclick="isLogin('up')">
        <span class="far fa-thumbs-up" aria-hidden="true" ></span>(<%=up%>)
    </button>
    <%
        }
    %>

    <%
        if (downState) {
    %>
    <button type="button" class="btn btn-default btn-lg" onclick="isLogin('down')">
        <span class="fas fa-thumbs-down" aria-hidden="true"></span>(<%=down%>)
    </button>
    <%
    }else {

    %>
    <button type="button" class="btn btn-default btn-lg" onclick="isLogin('down')">
        <span class="far fa-thumbs-down" aria-hidden="true"></span>(<%=down%>)
    </button>
    <%
        }
    %>

    <% int lastWeek = Integer.parseInt(week) -1;
    if (lastWeek > 0) {%>
    <button type="button" class="btn btn-default btn-lg" onclick=window.location.href="nextOrlast.do?week=<%=lastWeek%>">
        <span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>
    </button>
    <%}%>

    <button type="button" class="btn btn-default btn-lg" onclick=window.location.href="now.do">
        <span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span>

    </button>
    <% int nextWeek = Integer.parseInt(week) + 1;
        if (nextWeek < 62) {%>
    <button type="button" class="btn btn-default btn-lg" onclick=window.location.href="nextOrlast.do?week=<%=nextWeek%>">
        <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>
    </button>
    <%}%>



    <%if (login_info.equals("登录")) {
    %>
    <button type="button" class="btn btn-default btn-lg" onclick=window.location.href="<%=url%>" style="float: right;">
        <span class="fas fa-sign-in-alt" aria-hidden="true"></span>登录
    </button>
    <%}else {
    %>

    <div class="dropdown" style="float: right;">
        <button class="dropbtn"><%=info%></button>
        <div class="dropdown-content">
            <a href="#">个人中心</a>
            <a href="<%=url%>">退出登录</a>
        </div>
    </div>
    <%}%>


    <div>
        <div><img src="/static/TuringCalendar/TuringCalendar-<%=week%>.jpg" width="60%" ></div>
    </div>

</div>
</body>
</html>
