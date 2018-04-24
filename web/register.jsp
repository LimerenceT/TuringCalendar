<%--
  Created by IntelliJ IDEA.
  User: ql
  Date: 2018/4/15
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Register Page </title>
    <link rel="stylesheet" type="text/css" href="../static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../static/css/my-login.css">
    <script src="../static/js/md5.js"></script>
    <script language="javascript">
        function encrypt() {
            var password = document.getElementById("password");
            var md5_password = document.getElementById("md5_password");
            md5_password.value = hex_md5(password.value);
            $("#password").attr('disabled', 'true');
        }
    </script>
</head>


<body class="my-login-page">
<section class="h-100">
    <div class="container h-100">
        <div class="row justify-content-md-center h-100">
            <div class="card-wrapper">
                <div class="brand">
                    <img src="../static/img/logo.jpg">
                </div>
                <div class="card fat">
                    <div class="card-body">
                        <h4 class="card-title">Register</h4>
                        <form method="POST" action="register.do">
                            <a style="color:red"><%= request.getAttribute("message") == null ? "": request.getAttribute("message")%></a>

                            <div class="form-group">
                                <label for="name">Name</label>
                                <input id="name" type="text" class="form-control" name="name" required autofocus>
                            </div>

                            <%--<div class="form-group">--%>
                                <%--<label for="email">E-Mail Address</label>--%>
                                <%--<input id="email" type="email" class="form-control" name="email" required>--%>
                            <%--</div>--%>

                            <div class="form-group">

                                <label for="password">Password</label>
                                <input id="password" type="password" class="form-control" name="password" required data-eye>
                                <input id="md5_password" type="hidden" name="md5_password">

                            </div>

                            <%--<div class="form-group">--%>
                                <%--<label>--%>
                                    <%--<input type="checkbox" name="aggree" value="1"> I agree to the Terms and Conditions--%>
                                <%--</label>--%>
                            <%--</div>--%>

                            <div class="form-group no-margin">
                                <button type="submit" class="btn btn-primary btn-block" onclick="encrypt()">
                                    Register
                                </button>
                            </div>
                            <div class="margin-top20 text-center">
                                Already have an account? <a href="login.jsp">Login</a>
                            </div>
                        </form>

                    </div>
                </div>
                <div class="footer">
                    Copyright &copy; 2018 &mdash; ql
                </div>
            </div>
        </div>
    </div>
</section>


<script src="../static/js/jquery.min.js"></script>
<script src="../static/bootstrap/js/bootstrap.min.js"></script>
<script src="../static/js/my-login.js"></script>
</body>
</html>