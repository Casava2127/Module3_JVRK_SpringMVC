Ba lớp DTO (`RegisterDtoRequest`, `LoginDtoRequest`, `LoginDtoResponse`) này đóng vai trò quan trọng trong quá trình **đăng ký, đăng nhập** của người dùng trong hệ thống **Spring Boot**. Dưới đây là giải thích chi tiết chức năng của từng class:

---

## **1️⃣ RegisterDtoRequest** 📝
**📌 Chức năng:** Nhận dữ liệu từ client khi người dùng **đăng ký tài khoản mới**.

### 🔹 **Dữ liệu chứa:**
| **Thuộc tính** | **Chức năng** | **Validation** |
|--------------|-------------|--------------|
| `userName` | Tên đăng nhập | Không được để trống |
| `password` | Mật khẩu | Tối thiểu 6 ký tự |
| `fullName` | Họ và tên đầy đủ | Không được để trống |
| `email` | Địa chỉ email | Định dạng email hợp lệ, không để trống |
| `phone` | Số điện thoại | Phải theo định dạng số điện thoại Việt Nam |
| `sex` | Giới tính (Nam/Nữ) | `boolean` (true/false) |
| `bod` | Ngày sinh | Định dạng `yyyy-MM-dd` |
| `address` | Địa chỉ | Không được để trống |

### 🔹 **Cách hoạt động:**
1. Khi người dùng gửi yêu cầu đăng ký, dữ liệu từ form sẽ được ánh xạ vào **`RegisterDtoRequest`**.
2. Hệ thống sẽ kiểm tra xem thông tin có hợp lệ không dựa vào **validation annotations**.
3. Nếu hợp lệ, dữ liệu này sẽ được truyền đến **service** để xử lý việc đăng ký tài khoản.

---

## **2️⃣ LoginDtoRequest** 🔑
**📌 Chức năng:** Nhận dữ liệu đăng nhập từ client khi người dùng **đăng nhập vào hệ thống**.

### 🔹 **Dữ liệu chứa:**
| **Thuộc tính** | **Chức năng** | **Validation** |
|--------------|-------------|--------------|
| `userName` | Tên đăng nhập | Không được để trống |
| `password` | Mật khẩu | Tối thiểu 6 ký tự |

### 🔹 **Cách hoạt động:**
1. Khi người dùng nhập tên đăng nhập và mật khẩu rồi nhấn **"Đăng nhập"**, dữ liệu từ form sẽ được ánh xạ vào **`LoginDtoRequest`**.
2. Hệ thống kiểm tra thông tin hợp lệ (không trống, mật khẩu đủ dài).
3. Sau đó, backend sẽ kiểm tra thông tin đăng nhập với database.
    - Nếu đúng, hệ thống tạo **token** (JWT) và trả về thông tin người dùng.
    - Nếu sai, hệ thống trả về lỗi (`Sai tên đăng nhập hoặc mật khẩu`).

---

## **3️⃣ LoginDtoResponse** 🔄
**📌 Chức năng:** Trả về phản hồi từ server sau khi đăng nhập thành công.

### 🔹 **Dữ liệu chứa:**
| **Thuộc tính** | **Chức năng** |
|--------------|-------------|
| `userId` | ID của người dùng (được lấy từ database) |
| `userName` | Tên đăng nhập |
| `fullName` | Họ và tên đầy đủ |

### 🔹 **Cách hoạt động:**
1. Nếu thông tin đăng nhập hợp lệ, hệ thống sẽ tìm kiếm **user** trong database.
2. Backend sẽ tạo một đối tượng **`LoginDtoResponse`** chứa thông tin cần thiết và gửi về client.
3. Client nhận thông tin này và hiển thị tên người dùng lên giao diện (ví dụ: **"Chào mừng Nguyễn Văn A!"**).

---

## **💡 Tổng kết chức năng**
| **Class DTO** | **Chức năng** | **Dữ liệu chính** |
|--------------|-------------|-----------------|
| `RegisterDtoRequest` | Nhận thông tin khi **đăng ký tài khoản** | `userName`, `password`, `email`, `phone`, `address`, `sex`, `bod` |
| `LoginDtoRequest` | Nhận thông tin khi **đăng nhập** | `userName`, `password` |
| `LoginDtoResponse` | Trả về kết quả sau khi **đăng nhập thành công** | `userId`, `userName`, `fullName` |

---

### **🚀 Flow hoạt động tổng quát**
1️⃣ **Người dùng đăng ký** → Gửi thông tin bằng `RegisterDtoRequest` → Backend kiểm tra và lưu vào database.  
2️⃣ **Người dùng đăng nhập** → Gửi `LoginDtoRequest` → Backend kiểm tra thông tin.  
3️⃣ **Đăng nhập thành công** → Backend trả về `LoginDtoResponse` → Client hiển thị thông tin người dùng.

---

✅ **Ưu điểm của cách tiếp cận này:**
- **Tách biệt request & response** giúp code dễ đọc, dễ mở rộng.
- **Kiểm tra dữ liệu đầu vào** bằng validation, giảm lỗi do dữ liệu sai.
- **Bảo mật hơn** khi không trả về thông tin nhạy cảm (ví dụ: mật khẩu).
- **Dễ bảo trì** khi thay đổi hệ thống đăng nhập.

🚀 **Đây là cách làm chuẩn RESTful API trong Spring Boot!**