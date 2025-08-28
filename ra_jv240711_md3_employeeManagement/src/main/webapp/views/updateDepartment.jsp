<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Update Department</title>
</head>
<body>
<h1>Update Department</h1>
<form action="/departmentController/update" method="post">
    <input type="hidden" name="deptId" th:value="${department.deptId}">
    <label for="deptName">Name:</label>
    <input type="text" id="deptName" name="deptName" th:value="${department.deptName}" required>
    <br><br>
    <label for="deptDescription">Description:</label>
    <textarea id="deptDescription" name="deptDescription" th:text="${department.deptDescription}" required></textarea>
    <br><br>
    <label for="deptStatus">Status:</label>
    <select id="deptStatus" name="deptStatus">
        <option value="true" th:selected="${department.deptStatus}">Active</option>
        <option value="false" th:selected="${!department.deptStatus}">Inactive</option>
    </select>
    <br><br>
    <button type="submit">Update</button>
</form>
</body>
</html>
