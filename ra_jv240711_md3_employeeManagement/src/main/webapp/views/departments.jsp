<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Departments</title>
</head>
<body>
<h1>Departments List</h1>
<a href="/departmentController/initCreate">Create New Department</a>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Description</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${listDepartments}" var="dep" varStatus="loop">
    <!-- Loop through listDepartments -->
    <tr >
        <td>${loop.index+1}</td>
        <td>${dep.deptId}</td>
        <td>${dep.deptName}</td>
        <td>${dep.deptDescription}</td>
        <td>${dep.deptStatus ? 'Active' : 'Inactive'}</td>
        <td>
            <a th:href="@{/departmentController/initUpdate(id=${department.deptId})}">Edit</a>
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
