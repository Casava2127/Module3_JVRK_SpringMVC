<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Department</title>
</head>
<body>
<h1>Create New Department</h1>
<form action="${pageContext.request.contextPath}/departmentController/create" method="post">
    <label for="deptName">Name:</label>
    <input type="text" id="deptName" name="deptName" required>
    <br><br>
    <label for="deptDescription">Description:</label>
    <textarea id="deptDescription" name="deptDescription" required></textarea>
    <br><br>
    <label for="deptStatus">Status:</label>
    <select id="deptStatus" name="deptStatus">
        <option value="true">Active</option>
        <option value="false">Inactive</option>
    </select>
    <br><br>
    <button type="submit">Create</button>
</form>
</body>
</html>
