<%--
  Created by IntelliJ IDEA.
  User: This MC
  Date: 14/11/2024
  Time: 7:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create New Book</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/bookController/create" method="post">
    <label for="bookName">Book Name</label>
    <input type="text" id="bookName" name="bookName"/><br>
    <label for="price">Price</label>
    <input type="number" id="price" name="price"/><br>
    <label for="active">Status</label>
    <input type="radio" id="active" name="status" value="true" checked/><label for="active">Active</label>
    <input type="radio" id="inActive" name="status" value="false"/><label for="inActive">Inactive</label><br>
    <input type="submit" value="Create"/>
</form>
</body>
</html>
