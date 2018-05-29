<%@ page import="turing.Model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="turing.Model.Comment" %><%--
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
    <%--<link rel="stylesheet" type="text/css" href="../static/css/side-comments.css">--%>
    <%--<script src="../static/js/side-comments.js"></script>--%>

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
    List<Comment> comments = (List<Comment>) request.getAttribute("comments");
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
<div >
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
        <button id="button-1" class="dropbtn" value=<%=info%>><%=info%></button>
        <div class="dropdown-content">
            <a href="#">个人中心</a>
            <a href="<%=url%>">退出登录</a>
        </div>
    </div>
    <%}%>


    <div>
        <div style="float: left"><img src="/static/TuringCalendar/TuringCalendar-<%=week%>.jpg" width="65%" ></div>
    </div>
    <link rel="stylesheet" type="text/css" href="../static/css/social.css"/>
    <script type="text/javascript" src="../static/js/social.js"></script>

    <div class="list" id="list" style="float: right; margin-top: 10%; margin-right: 10%">
        <div class="box clearfix">
            <%--<img src="images/s1.jpg" alt="" class="head" /><!-- 头像 -->--%>
            <div class="content">
                <div class="main">
                    <p class="text">
                        <span style="font-style: oblique; font-size: 30px">Comments：</span>
                    </p>
                </div><!-- 发布内容 -->

                <div class="comment-list">
                    <div class="comment-box  clearfix">
                            <%  if (comments != null){
                                for (Comment comment : comments) {
                                    String content = comment.getContent();
                                    String time = comment.getCreate_time();
                                    String username = comment.getUsername();
                                %>
                                    <div class="comment-content" style="margin-top: 20px; margin-left: 30px">
                                        <p class="comment-text"><span class="user"><%=username%>：</span><%=content%></p>
                                        <p class="comment-time"><%=time%></p>
                                    </div>
                            <%}
                            }%>



                    </div>
                </div><!-- 评论列表 -->
                <div class="text-box">
                    <textarea autocomplete="off" class="comment">评论......</textarea>
                    <button class="btn">发表</button>
                    <span class="word"><span class="length">0</span>/140</span>
                </div><!-- 发表评论 -->
            </div><!-- 正文内容 -->
        </div><!-- 第一个box结束 -->




    </div><!-- 社交评论列表 -->




<button id="week" value=<%=week%>></button>
</div>
</body>
</html>
