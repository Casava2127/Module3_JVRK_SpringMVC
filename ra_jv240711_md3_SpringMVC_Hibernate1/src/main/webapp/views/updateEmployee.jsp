<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Employee</title>
</head>
<body>
<h1>Update Employee Information</h1>

<!-- Hiển thị thông báo lỗi nếu dữ liệu không được tải -->
<c:if test="${empty empUpdate || empty listDepartments}">
    <p style="color: red;">Unable to load employee or department data. Please try again later.</p>
</c:if>

<!-- Form cập nhật nhân viên -->
<form action="${pageContext.request.contextPath}/employeeController/update" method="post">
    <!-- Trường empId (readonly, không thể chỉnh sửa) -->
    <%--@declare id="status"--%><label for="empId">Employee ID:</label>
    <input type="text" id="empId" name="empId" value="${empUpdate.empId}" readonly/><br/>

    <!-- Trường empName -->
    <label for="empName">Name:</label>
    <input type="text" id="empName" name="empName" value="${empUpdate.empName}" required/><br/>

    <!-- Dropdown danh sách phòng ban -->
    <label for="deptId">Department:</label>
    <select id="deptId" name="deptId" required>
        <c:forEach var="dept" items="${listDepartments}">
            <option value="${dept.deptId}" ${dept.deptId == empUpdate.department.deptId ? 'selected' : ''}>
                    ${dept.deptName}
            </option>
        </c:forEach>
    </select>
    <!-- Trường Salary -->
    <label for="salary">Salary:</label>
    <input type="number" id="salary" name="salary" value="${empUpdate.salary}" required/><br/>

    <!-- Trường Status -->
    <label for="status">Status:</label><br>
    <input type="radio" id="statusActive" name="status" value="true" ${empUpdate.status ? 'checked' : ''}> Active
    <input type="radio" id="statusInactive" name="status" value="false" ${!empUpdate.status ? 'checked' : ''}> Inactive


    <!-- Nút Submit -->
    <input type="submit" value="Update"/>
</form>

<!-- Liên kết quay lại danh sách nhân viên -->
<p><a href="${pageContext.request.contextPath}/employeeController/findAll">Back to Employee List</a></p>
</body>
</html>
