<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: This MC
  Date: 26/11/2024
  Time: 7:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<style>
    .error {
        color: red;
    }
</style>
<form:form modelAttribute="userLogin" action="${pageContext.request.contextPath}/userController/login" method="post">
    <form:label path="userName" cssErrorClass="error" for="userName">UserName</form:label>
    <form:input path="userName" id="userName"/>
    <form:errors path="userName" cssClass="error"/><br>
    <form:label path="password" cssErrorClass="error" for="password">Password</form:label>
    <form:password path="password" id="password"/>
    <form:errors path="password" cssClass="error"/><br>
    <input type="submit" value="Login"/>
</form:form>
<a href="<%=request.getContextPath()%>/userController/initRegister">Create Account</a>
</body>
</html>
