<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: This MC
  Date: 26/11/2024
  Time: 8:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<style>
    .error {
        color: red;
    }
</style>
<h3>Register account</h3>
<form:form modelAttribute="userRegister" action="${pageContext.request.contextPath}/userController/register"
           method="post">
    <form:label path="userName" cssErrorClass="error" for="userName">UserName</form:label>
    <form:input path="userName" id="userName"/>
    <form:errors path="userName" cssClass="error"/><br>
    <form:label path="password" cssErrorClass="error" for="password">Password</form:label>
    <form:password path="password" id="password"/>
    <form:errors path="password" cssClass="error"/><br>
    <form:label path="fullName" cssErrorClass="error" for="fullName">Fullname</form:label>
    <form:input path="fullName" id="fullName"/>
    <form:errors path="fullName" cssClass="error"/><br>
    <form:label path="email" cssErrorClass="error" for="email">Email</form:label>
    <form:input path="email" id="email"/>
    <form:errors path="email" cssClass="error"/><br>
    <form:label path="phone" cssErrorClass="error" for="phone">Phone</form:label>
    <form:input path="phone" id="phone"/>
    <form:errors path="phone" cssClass="error"/><br>
    <form:label path="sex" for="male">Sex</form:label>
    <form:radiobutton path="sex" id="male" label="Male" value="true"/>
    <form:radiobutton path="sex" id="female" label="Female" value="false"/><br>
    <form:label path="bod" for="bod">BOD</form:label>
    <input type="date" id="bod" name="bod"/><br>
    <form:label path="address" cssErrorClass="error" for="address">Address</form:label>
    <form:input path="address" id="address"/>
    <form:errors path="address" cssClass="error"/><br>
    <input type="submit" value="Register"/>
</form:form>
</body>
</html>
