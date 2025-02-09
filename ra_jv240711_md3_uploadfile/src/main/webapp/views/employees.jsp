<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: This MC
  Date: 03/12/2024
  Time: 8:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List Employees</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Employee ID</th>
        <th>Employee Name</th>
        <th>Avatar</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${listEmployees}" var="emp">
        <tr>
            <td>${emp.empId}</td>
            <td>${emp.empName}</td>
            <td><img src="${emp.avatarLink}" alt="${emp.empName}" width="100" height="100"/></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
