<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create New Employee</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        form {
            max-width: 400px;
            margin: auto;
        }
        h1 {
            text-align: center;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input, textarea, select {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        textarea {
            resize: vertical;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 16px;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<h1>Create New Employee</h1>
<form action="<%=request.getContextPath()%>/employeeController/create" method="post">
    <!-- Emp_id: Primary Key -->
    <label for="empId">EmpId:</label>
    <input type="text" id="empId" name="empId" maxlength="5" required/>

    <!-- Emp_Name: Not null -->
    <label for="empName">EmpName:</label>
    <input type="text" id="empName" name="empName" maxlength="100" required/>

    <!-- Emp_bod: Not null -->
    <label for="bod">Birth Of Date:</label>
    <input type="date" id="bod" name="bod" required/>

    <!-- Emp_address: Not null -->
    <label for="empAddress">Address:</label>
    <textarea id="empAddress" name="empAddress" rows="3" required></textarea>

    <!-- Emp_phone: Not null, unique -->
    <label for="empPhone">Phone:</label>
    <input type="text" id="empPhone" name="empPhone" maxlength="15" required/>

    <!-- Emp_rate: Not null, check > 0 -->
    <label for="empRate">Salary Rate:</label>
    <input type="number" id="empRate" name="empRate" step="0.01" min="0.01" required/>

    <!-- Dept_id: Foreign Key -->
    <label for="deptId">Choose Department:</label>
    <select id="deptId" name="deptId" required>
        <c:forEach items="${listDepartments}" var="dept">
            <option value="${dept.deptId}">${dept.deptName}</option>
        </c:forEach>
    </select>

    <!-- Emp_status: Not null -->
    <label for="status">Choose Status:</label>
    <select id="status" name="status" required>
        <option value="0">Đang làm việc</option>
        <option value="1">Nghỉ chế độ</option>
        <option value="2">Nghỉ việc</option>
    </select>

    <!-- Submit -->
    <input type="submit" value="Create"/>
</form>
</body>
</html>
