<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: This MC
  Date: 14/11/2024
  Time: 7:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List Books</title>
</head>
<body>
<h3>List Books</h3>
<table border="1">
    <thead>
    <tr>
        <th>No</th>
        <th>Book Id</th>
        <th>Book Name</th>
        <th>Price</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${listBooks}" var="book" varStatus="loop">
        <tr>
            <td>${loop.index+1}</td>
            <td>${book.bookId}</td>
            <td>${book.bookName}</td>
            <td>${book.price}</td>
            <td>${book.status?"Active":"Inactive"}</td>
            <td>
                <a href="<%=request.getContextPath()%>/bookController/initUpdate?bookId=${book.bookId}">Update</a>
                <a href="<%=request.getContextPath()%>/bookController/delete?bookId=${book.bookId}">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="<%=request.getContextPath()%>/bookController/initCreate">Create new Book</a>
</body>
</html>
