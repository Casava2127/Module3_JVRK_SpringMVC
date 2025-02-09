<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: This MC
  Date: 03/12/2024
  Time: 7:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Employee</title>
</head>
<body>
<form:form modelAttribute="employee" action="${pageContext.request.contextPath}/employeeController/create" method="post"
           enctype="multipart/form-data">
    <form:label path="empId">Employee ID</form:label>
    <form:input path="empId"/><br>
    <form:label path="empName">Employee Name</form:label>
    <form:input path="empName"/><br>
    <form:label path="avatar">Avatar</form:label>
    <form:input type="file" path="avatar"/><br>
    <input type="submit" value="Create"/>
</form:form>
</body>
</html>
