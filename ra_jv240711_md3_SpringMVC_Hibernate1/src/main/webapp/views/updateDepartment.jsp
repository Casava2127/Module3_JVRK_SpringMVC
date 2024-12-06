
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Đặt loại nội dung của trang web là HTML và mã hóa ký tự là UTF-8 để hỗ trợ các ký tự đặc biệt (chẳng hạn như tiếng Việt). -->
<!-- `language="java"` cho biết mã trong JSP sử dụng ngôn ngữ Java. -->

<html>
<head>
    <title>Update Department</title>
    <!-- Tiêu đề trang web khi hiển thị trên tab của trình duyệt -->
</head>
<body>
<form action="<%=request.getContextPath()%>/departmentController/update" method="post">
    <!-- Đây là một form gửi yêu cầu POST đến đường dẫn "/departmentController/update" để thực hiện việc cập nhật phòng ban. -->
    <!-- `request.getContextPath()` trả về context path của ứng dụng, giúp tạo đường dẫn chính xác cho form. -->

    Dept ID: <input type="text" id="deptId" name="deptId" value="${deptUpdate.deptId}" readonly/> <br>
    <!-- Trường hiển thị ID phòng ban (`deptId`) của phòng ban cần cập nhật. Giá trị này không thể chỉnh sửa, vì có thuộc tính `readonly`. -->
    <!-- `${deptUpdate.deptId}` là cú pháp EL (Expression Language) để lấy giá trị từ thuộc tính `deptId` của đối tượng `deptUpdate` trong model (được gửi từ controller). -->

    Dept Name: <input type="text" id="deptName" name="deptName" value="${deptUpdate.deptName}"/> <br>
    <!-- Trường nhập tên phòng ban (`deptName`). Giá trị được điền vào từ đối tượng `deptUpdate`. Người dùng có thể sửa tên phòng ban. -->

    Description: <input type="text" id="description" name="description" value="${deptUpdate.description}"/> <br>
    <!-- Trường nhập mô tả phòng ban (`description`). Người dùng có thể sửa mô tả phòng ban. -->

    Dept Status: <input type="text" id="status" name="status" value="${deptUpdate.status}"/> <br>
    <!-- Trường nhập trạng thái phòng ban (`status`). Người dùng có thể sửa trạng thái phòng ban. -->
    <!-- `status` có thể là giá trị boolean (true/false), nhưng ở đây nó đang được hiển thị dưới dạng văn bản. -->

    <input type="submit" value="Update"/>
    <!-- Nút gửi form để cập nhật thông tin phòng ban. -->
</form>
</body>
</html>
