<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Đưa thư viện JSTL vào trang JSP, cho phép sử dụng các thẻ JSTL (c:forEach, c:if, ...) trong trang -->
<!-- `prefix="c"` có nghĩa là các thẻ JSTL sẽ được sử dụng với tiền tố "c" (ví dụ: `c:forEach>`) -->

    <%--
      Created by IntelliJ IDEA.
      User: This MC
      Date: 21/11/2024
      Time: 7:49 PM
      To change this template use File | Settings | File Templates.
    --%>
    <!-- Các comment này là thông tin do IDE IntelliJ IDEA tự động tạo ra khi tạo trang JSP mới. Không ảnh hưởng đến việc thực thi mã -->

    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <!-- Đặt loại nội dung của trang web là HTML và mã hóa ký tự là UTF-8 (hỗ trợ các ký tự đặc biệt như tiếng Việt) -->
    <!-- `language="java"` cho biết mã trong JSP sử dụng ngôn ngữ Java -->

    <html>
    <head>
    <title>List Department</title>
    <!-- Tiêu đề trang web khi hiển thị trên tab của trình duyệt -->
    </head>
    <body>
    <table border="1">
    <!-- Tạo bảng với viền -->
    <thead>
    <tr>
    <th>No</th>
    <th>Department Id</th>
    <th>Department Name</th>
    <th>Description</th>
    <th>Status</th>
    <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <!-- Bắt đầu phần thân bảng, nơi danh sách các phòng ban sẽ được hiển thị -->

    <c:forEach items="${listDepartments}" var="dept" varStatus="loop">
        <!-- Sử dụng thẻ JSTL c:forEach> để duyệt qua danh sách phòng ban -->
        <!-- `items="${listDepartments}"`: Dữ liệu được duyệt qua là danh sách phòng ban (listDepartments) từ model -->
        <!-- `var="dept"`: Mỗi phần tử trong danh sách sẽ được lưu trữ vào biến `dept` -->
        <!-- `varStatus="loop"`: Cung cấp thông tin về trạng thái vòng lặp (số lần lặp, v.v.) -->

        <tr>
            <td>${loop.index+1}</td>
            <!-- In ra số thứ tự (index) của phòng ban trong danh sách, cộng thêm 1 để bắt đầu từ 1 thay vì 0 -->

            <td>${dept.deptId}</td>
            <!-- In ra ID của phòng ban -->

            <td>${dept.deptName}</td>
            <!-- In ra tên phòng ban -->

            <td>${dept.description}</td>
            <!-- In ra mô tả phòng ban -->

            <td>${dept.status?"Active":"Inactive"}</td>
            <!-- In ra trạng thái của phòng ban:
                 Nếu `dept.status` là `true` (Active), sẽ hiển thị "Active", nếu là `false`, hiển thị "Inactive" -->

            <td>
                <!-- Thêm cột hành động với các liên kết để cập nhật hoặc xóa phòng ban -->
                <a href="<%=request.getContextPath()%>/departmentController/initUpdate?deptId=${dept.deptId}">Update</a>
                <!-- Liên kết để chuyển đến trang cập nhật phòng ban, với tham số `deptId` là ID phòng ban cần cập nhật -->

                <a href="<%=request.getContextPath()%>/departmentController/delete?deptId=${dept.deptId}">Delete</a>
                <!-- Liên kết để xóa phòng ban, với tham số `deptId` là ID phòng ban cần xóa -->
            </td>
        </tr>
    </c:forEach>
        <!-- Kết thúc vòng lặp -->
        </tbody>
        </table>
        </body>
        </html>
