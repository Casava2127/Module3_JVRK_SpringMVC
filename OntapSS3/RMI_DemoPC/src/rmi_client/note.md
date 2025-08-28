Ok 👍 mình viết lại chuẩn theo tình huống bạn có **hai máy khác nhau** (1 máy chạy server, 1 máy chạy client) với IP của **server = 192.168.1.226**.

---

### 1. Trên **cả 2 máy** (server + client)

> Chỉ cần compile một lần, sau đó copy thư mục `out` sang cả hai máy.

```cmd
:: Tại thư mục gốc của project
dir /s /b src\*.java > sources.txt
javac -d out @sources.txt
```

---

### 2. Trên **máy server** (IP: 192.168.1.226)

```cmd
java -cp out -Djava.rmi.server.hostname=192.168.1.226 rmi_server.ServerMain
```

Nếu chạy ok, bạn sẽ thấy log kiểu:
`RMI Student Server đang chạy...`

---

### 3. Trên **máy client**

Trong code `ClientMain.java` phải có dòng:

```java
Registry registry = LocateRegistry.getRegistry("192.168.1.226", 1099);
```

Sau đó chạy:

```cmd
java -cp out rmi_client.ClientMain
```

---

### 4. Nếu muốn test trên **cùng 1 máy** (khỏi lo firewall, network)

Server:

```cmd
java -cp out -Djava.rmi.server.hostname=localhost rmi_server.ServerMain
```

Client:

```cmd
java -cp out rmi_client.ClientMain
```

---

👉 Như vậy:

* **Server**: luôn chạy với `-Djava.rmi.server.hostname=<IP thật>`
* **Client**: kết nối đến IP đó trong code.

---

Bạn muốn mình viết luôn cách **tự động detect IP server trong code** (khỏi phải hardcode 192.168.1.226) để sau này đổi mạng không cần sửa tay không?
Ok 👍 mình sẽ viết đầy đủ từng bước cho bạn để **RMI chạy được giữa 2 máy khác nhau (server & client)**.

---

## 1. Biên dịch code RMI (interface, server, client)

### Trên cả **server** và **client**, đều phải có file `Student.java`, `StudentManager.java` (interface), …

Giả sử project nằm ở thư mục `RMI_Demo` với cấu trúc:

```
RMI_Demo/
 ├── src/
 │    ├── rmi_interface/
 │    │     └── StudentManager.java
 │    ├── rmi_server/
 │    │     └── ServerMain.java
 │    └── rmi_client/
 │          └── ClientMain.java
 └── out/
```

### Lệnh biên dịch (chạy ở cả server & client máy)

```cmd
cd C:\Users\Admin\Desktop\Ky9\JVRMI\RMI_Demo
dir /s /b src\*.java > sources.txt
javac -d out @sources.txt
```

👉 Sau khi biên dịch:

* Máy **server** giữ code `ServerMain` + `interface`.
* Máy **client** chỉ cần `ClientMain` + `interface`.

---

## 2. Mở tường lửa trên **máy server**

RMI mặc định dùng port **1099** (Registry).
Nếu bạn chạy trên port khác (vd: 2000) thì thay số port cho đúng.

```cmd
netsh advfirewall firewall add rule name="RMI Registry 1099" dir=in action=allow protocol=TCP localport=1099
```

Nếu dùng port 2000 thì:

```cmd
netsh advfirewall firewall add rule name="RMI Registry 2000" dir=in action=allow protocol=TCP localport=2000
```

👉 Lưu ý: **chỉ cần mở tường lửa trên máy server**, không cần mở ở client.

---

## 3. Lệnh chạy **Server**

Ví dụ server có IP LAN là `192.168.1.226`:

```cmd
cd C:\Users\Admin\Desktop\Ky9\JVRMI\RMI_Demo
java -cp out -Djava.rmi.server.hostname=192.168.1.226 rmi_server.ServerMain
```

* `-Djava.rmi.server.hostname=192.168.1.226` để cho client ngoài mạng truy cập được.
* Server sẽ lắng nghe port `1099` (hoặc port bạn set trong code `LocateRegistry.createRegistry(port)`).

---

## 4. Lệnh chạy **Client**

Trên máy client (ví dụ ở thư mục `C:\D\VscodePC\Java\TuHocJV\Module3_JVRK_SpringMVC\OntapSS3\RMI_DemoPC`):

```cmd
cd C:\D\VscodePC\Java\TuHocJV\Module3_JVRK_SpringMVC\OntapSS3\RMI_DemoPC
java -cp out rmi_client.ClientMain
```

Trong code `ClientMain` bạn nhớ kết nối đúng IP server:

```java
Registry registry = LocateRegistry.getRegistry("192.168.1.226", 1099);
StudentManager stub = (StudentManager) registry.lookup("StudentManager");
```

---

✅ Tóm lại:

1. **Biên dịch ở cả 2 máy:**

```cmd
dir /s /b src\*.java > sources.txt
javac -d out @sources.txt
```

2. **Mở port trên server:**

```cmd
netsh advfirewall firewall add rule name="RMI Registry 1099" dir=in action=allow protocol=TCP localport=1099
```

3. **Chạy server (trên máy 192.168.1.226):**

```cmd
java -cp out -Djava.rmi.server.hostname=192.168.1.226 rmi_server.ServerMain
```

4. **Chạy client (ở máy khác):**

```cmd
java -cp out rmi_client.ClientMain
```

---

👉 Bạn có muốn mình viết luôn **script .bat cho server và client** để chỉ cần double click chạy, không phải gõ lại lệnh mỗi lần?
Câu hỏi rất hay 👍

Khi bạn mở port bằng `netsh advfirewall`, tức là bạn đã **thêm một rule cho phép kết nối từ bên ngoài vào máy server qua cổng đó**.

👉 Nếu bạn chỉ dùng RMI để học tập / thử nghiệm trong LAN thì:

* **Nên đóng lại (xóa rule)** sau khi dùng xong để tránh rủi ro bảo mật.
* Vì khi để mở port 1099 mãi mãi, bất kỳ ai trong mạng LAN (hoặc nếu router mở port ra Internet) cũng có thể thử kết nối tới server của bạn.

---

## Cách xóa rule (đóng lại) sau khi dùng:

Ví dụ rule đã mở:

```cmd
netsh advfirewall firewall add rule name="RMI Registry 1099" dir=in action=allow protocol=TCP localport=1099
```

Muốn xóa đi:

```cmd
netsh advfirewall firewall delete rule name="RMI Registry 1099"
```

---

✅ Tóm lại:

* Nếu bạn chỉ chạy test → **mở port khi cần, chạy xong thì xóa rule**.
* Nếu bạn làm môi trường production (server chạy lâu dài) → giữ rule, nhưng nên **giới hạn IP client** được phép kết nối (ví dụ chỉ cho phép `192.168.1.50` truy cập).

Bạn có muốn mình hướng dẫn luôn cách **chỉ cho 1 IP client kết nối tới port RMI** (thay vì mở toàn mạng LAN)?
v