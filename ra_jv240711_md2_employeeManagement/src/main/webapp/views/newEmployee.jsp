<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: This MC
  Date: 19/11/2024
  Time: 7:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Employee</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/employeeController/create" method="post">
    EmpId:<input type="text" id="empId" name="empId"/><br>
    EmpName:<input type="text" id="empName" name="empName"/><br>
    Birth Of Date:<input type="date" id="bod" name="bod"/><br>
    Chose Department:
    <select id="deptId" name="deptId">
        <c:forEach items="${listDepartments}" var="dept">
            <option value="${dept.deptId}">${dept.deptName}</option>
        </c:forEach>
    </select><br>
    Chose Status:
    <select id="status" name="status">
        <option value="0">Đang làm việc</option>
        <option value="1">Nghỉ chế độ</option>
        <option value="2">Nghỉ việc</option>
    </select><br>
    <input type="submit" value="Create"/>
</form>
</body>
</html>
