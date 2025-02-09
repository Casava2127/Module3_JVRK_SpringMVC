Hai lớp `RegisterMapper` và `LoginMapper` đóng vai trò **chuyển đổi dữ liệu (mapping)** giữa các lớp **DTO (Data Transfer Object)** và **Entity** trong hệ thống. Chúng giúp hệ thống **chuyển dữ liệu từ request vào entity để lưu trữ, và từ entity sang response để gửi lại client**.

---

# **1️⃣ RegisterMapper** 📝
**📌 Chức năng:**
- **Chuyển đổi từ `RegisterDtoRequest` ➝ `User` (Entity)**: Khi người dùng đăng ký, hệ thống cần lấy dữ liệu từ request và chuyển vào entity để lưu vào database.
- **Chuyển đổi từ `User` ➝ Response (`P`)**: Hiện tại phương thức này chưa hoàn thiện (trả về `null`).

### 🔹 **Cách hoạt động**
1. **Khi người dùng gửi yêu cầu đăng ký**, dữ liệu từ `RegisterDtoRequest` được truyền vào `mapperRequestToEntity()`.
2. Hệ thống tạo một đối tượng `User` mới và gán các giá trị từ request vào entity.
3. Entity `User` sẽ được lưu vào database thông qua service.
4. (Tùy chỉnh sau này) `mapperEntityToResponse()` có thể chuyển entity `User` sang một DTO phản hồi cho client.

---

# **2️⃣ LoginMapper** 🔑
**📌 Chức năng:**
- **Chuyển đổi từ `LoginDtoRequest` ➝ `User` (Entity)**: Khi người dùng nhập thông tin đăng nhập, hệ thống cần kiểm tra dữ liệu này trong database.
- **Chuyển đổi từ `User` (Entity) ➝ `LoginDtoResponse`**: Nếu đăng nhập thành công, hệ thống cần trả về thông tin người dùng.

### 🔹 **Cách hoạt động**
1. **Khi người dùng nhập tài khoản, mật khẩu**, dữ liệu từ `LoginDtoRequest` được truyền vào `mapperRequestToEntity()`.
2. Hệ thống tạo đối tượng `User` với thông tin vừa nhận.
3. Hệ thống kiểm tra tài khoản này trong database.
4. **Nếu đúng thông tin**, `mapperEntityToResponse()` sẽ chuyển entity `User` thành `LoginDtoResponse` và gửi về client.

---

# **💡 Tổng kết**
| **Mapper** | **Chức năng chính** | **Phương thức quan trọng** |
|------------|------------------|----------------|
| `RegisterMapper` | Chuyển `RegisterDtoRequest` → `User` khi đăng ký | `mapperRequestToEntity()` |
| `LoginMapper` | Chuyển `LoginDtoRequest` → `User` khi đăng nhập | `mapperRequestToEntity()` |
| `LoginMapper` | Chuyển `User` → `LoginDtoResponse` để phản hồi | `mapperEntityToResponse()` |

🚀 **Lợi ích của việc sử dụng Mapper:**
✅ **Tách biệt rõ ràng** giữa DTO và Entity.  
✅ **Dễ dàng bảo trì** khi cần thay đổi logic hoặc dữ liệu.  
✅ **Tái sử dụng code tốt hơn**, giúp hệ thống gọn gàng hơn.