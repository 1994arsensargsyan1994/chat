<%--
  Created by IntelliJ IDEA.
  User: arsen
  Date: 16.04.20
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/login" method="post">
    <% if (request.getSession().getAttribute("successfully") != null){%>
    <span style="color: blue">
        <%=request.getSession().getAttribute("successfully")%></span>
        <% request.getSession().removeAttribute("successfully");%>
    <%}%>
    <label for="email">Email</label>
    <input id="email" name="email" type="text"/>
    <br>
    <label for="password">Password</label>
    <input id="password" name="password" type="password"/>
    <br>
    <input type="submit" name="login">
    <a href="/register">Register</a>

</form>
</body>
</html>
