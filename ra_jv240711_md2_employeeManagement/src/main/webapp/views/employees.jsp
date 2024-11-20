<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: This MC
  Date: 19/11/2024
  Time: 7:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List employees</title>
</head>
<body>
<h3>List Employees</h3>
<table border="1">
    <thead>
    <tr>
        <th>No</th>
        <th>Employee Id</th>
        <th>Employee Name</th>
        <th>Birth Of Date</th>
        <th>Department Id</th>
        <th>Department Name</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${listEmployees}" var="emp" varStatus="loop">
        <tr>
            <td>${loop.index+1}</td>
            <td>${emp.empId}</td>
            <td>${emp.empName}</td>
            <td><fmt:formatDate value="${emp.bod}" pattern="dd/MM/yyyy"/></td>
            <td>${emp.deptId}</td>
            <td>${emp.deptName}</td>
            <td>${emp.status==0?"Đang làm việc":(emp.status==1?"Nghỉ chế độ":"Nghỉ việc")}</td>
            <td></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="<%=request.getContextPath()%>/employeeController/initCreate">Create new employee</a>
</body>
</html>
