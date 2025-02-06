<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List Employee</title>
</head>
<body>
<h1>List of Employees</h1>
<table border="1">
    <thead>
    <tr>
        <th>No</th>
        <th>Employee ID</th>
        <th>Employee Name</th>
        <th>Employee Day</th>
        <th>Employee Salary</th>
        <th>Employee Status</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${ListEmployees}" var="emp" varStatus="loop">
        <tr>
            <td>${loop.index + 1}</td>
            <td>${emp.empId}</td>
            <td>${emp.empName}</td>
            <td>${emp.bod}</td>
            <td>${emp.salary}</td>
            <td>${emp.status}</td>
            <td>
                <!-- Liên kết đến trang cập nhật -->
                <a href="${pageContext.request.contextPath}/employeeController/initUpdate?empId=${emp.empId}">Update</a>
                <!-- Liên kết để xóa nhân viên -->
                <a href="${pageContext.request.contextPath}/employeeController/delete?empId=${emp.empId}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
