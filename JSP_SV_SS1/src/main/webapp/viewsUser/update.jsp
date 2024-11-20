<%--
  Created by IntelliJ IDEA.
  User: This MC
  Date: 12/11/2024
  Time: 7:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Categories</title>
</head>
<body>
<form action="UserController?action=update" method="post">
    <label for="userId">user Id</label>
    <input type="text" id="userId" name="userId" value="${user.userId}" readonly/><br/>
    <label for="userName">user Name</label>
    <input type="text" id="userName" name="userName" value="${user.userName}"/><br/>
    <label for="description">Description</label>
    <input type="text" id="description" name="description" value="${user.description}"/><br/>
    <label>Status</label>
    <input type="radio" id="active" name="status" value="true" ${user.status?'checked':''}/><label for="active">Active</label>
    <input type="radio" id="inActive" name="status" value="false" ${user.status?'':'checked'}/><label for="inActive">Inactive</label><br>
    <input type="submit" value="Update"/>
</form>
</body>
</html>
