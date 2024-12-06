<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%--&lt;%&ndash;--%>
<%--  Created by IntelliJ IDEA.--%>
<%--  User: This MC--%>
<%--  Date: 19/11/2024--%>
<%--  Time: 7:17 PM--%>
<%--  To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>List employees</title>--%>
<%--</head>--%>
<%--<body>--%>
<%--<h3>List Employees</h3>--%>
<%--<table border="1">--%>
<%--    <thead>--%>
<%--    <tr>--%>
<%--        <th>No</th>--%>
<%--        <th>Employee Id</th>--%>
<%--        <th>Employee Name</th>--%>
<%--        <th>Birth Of Date</th>--%>
<%--        <th>Department Id</th>--%>
<%--        <th>Employee Rate</th>--%>
<%--        <th>Status</th>--%>
<%--        <th>Actions</th>--%>
<%--    </tr>--%>
<%--    </thead>--%>
<%--    <tbody>--%>
<%--    <c:forEach items="${listEmployees}" var="emp" varStatus="loop">--%>
<%--        <tr>--%>
<%--            <td>${loop.index+1}</td>--%>
<%--            <td>${emp.empId}</td>--%>
<%--            <td>${emp.empName}</td>--%>
<%--            <td><fmt:formatDate value="${emp.bod}" pattern="dd/MM/yyyy"/></td>--%>
<%--            <td>${emp.deptId}</td>--%>
<%--            <td>${emp.empRate}</td>--%>
<%--            <td>${emp.status==0?"Đang làm việc":(emp.status==1?"Nghỉ chế độ":"Nghỉ việc")}</td>--%>
<%--            <td></td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>
<%--    </tbody>--%>
<%--</table>--%>
<%--<a href="<%=request.getContextPath()%>/employeeController/initCreate">Create new employee</a>--%>
<%--</body>--%>
<%--</html>--%>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List Employees</title>
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
        <th>Address</th>
        <th>Phone</th>
        <th>Salary Rate</th>
        <th>Department Id</th>
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
            <td>${emp.empAddress}</td>
            <td>${emp.empPhone}</td>
            <td>${emp.empRate}</td>
            <td>${emp.deptId}</td>
            <td>${emp.status == 0 ? "Đang làm việc" : (emp.status == 1 ? "Nghỉ chế độ" : "Nghỉ việc")}</td>
            <td>
                <!-- Example action buttons, such as Edit and Delete -->
                <a href="<%=request.getContextPath()%>/employeeController/initEdit?empId=${emp.empId}">Edit</a> |
                <a href="<%=request.getContextPath()%>/employeeController/delete?empId=${emp.empId}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="<%=request.getContextPath()%>/employeeController/initCreate">Create new employee</a>
</body>
</html>
