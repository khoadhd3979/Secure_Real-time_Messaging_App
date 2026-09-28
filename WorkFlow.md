
# Quy trình Làm việc nhóm & Phân công Dự án (SecureChat-Java)

Tài liệu này quy định luồng làm việc với Git/GitHub và chi tiết phân công công việc cho từng giai đoạn của dự án.

---

### 1. Chiến lược Quản lý Nhánh (Branching)

Tuyệt đối **KHÔNG** code trực tiếp hoặc push thẳng lên nhánh `main`.

* **`main`**: Nhánh trung tâm chứa code ổn định, đã qua kiểm thử và chạy hoàn chỉnh.
* **`feature/khoa-server-db`**: Nhánh làm việc độc lập của Khoa (Xử lý Backend Server, Database, Cryptography).
* **`feature/tho-client-gui`**: Nhánh làm việc độc lập của Sự (Xử lý Client GUI, Logic luồng màn hình, Đóng gói gói tin).

---

### 2. Quy trình làm việc hằng ngày (Daily Workflow)

Mỗi khi bắt đầu hoặc kết thúc một buổi code, các thành viên thực hiện theo vòng lặp sau trên máy tính cá nhân:

### Bước 1: Đảm bảo đang đứng đúng nhánh của mình
```bash
git checkout feature/ten-nhanh-cua-ban

```

### Bước 2: Viết code và Lưu thay đổi

Sau khi hoàn thành một chức năng nhỏ (ví dụ: tạo form đăng nhập, tạo kết nối CSDL), đưa các file vào trạng thái chuẩn bị:

```bash
git add .

```

### Bước 3: Commit (Lưu lịch sử cục bộ)

Ghi chú rõ ràng, ngắn gọn về chức năng vừa làm:

```bash
git commit -m "Mô tả ngắn gọn chức năng vừa làm (VD: Thêm giao diện chat nhóm)"

```

### Bước 4: Push (Đẩy lên GitHub)

Đẩy code lên đúng nhánh làm việc của mình:

```bash
git push origin feature/ten-nhanh-cua-ban

```

---

## 3. Quy trình Gộp Code vào nhánh Main (Pull Request)

Khi một module chức năng đã hoàn thiện và chạy thông suốt trên nhánh cá nhân, tiến hành gộp vào nhánh chung:

1. Truy cập kho lưu trữ GitHub của dự án trên trình duyệt.
2. Chuyển sang nhánh cá nhân của bạn, bấm nút **Compare & pull request**.
3. Đợi thành viên còn lại kiểm tra (review) code. Nếu không có xung đột, bấm **Merge pull request** để gộp mã nguồn vào nhánh `main`.
4. **Đồng bộ code về máy tính:** Thành viên còn lại (hoặc cả hai) cần kéo code mới nhất từ `main` về máy để tiếp tục làm việc trên nền code mới nhất:
```bash
git checkout main
git pull origin main
git checkout feature/ten-nhanh-cua-ban

```



---

## 4. Phân công Công việc Chi tiết

Dự án áp dụng chiến lược phát triển: **"Xây dựng luồng nhắn tin thời gian thực (text thô) trước, sau đó tích hợp mã hóa (RSA/AES) vào sau"** nhằm đảm bảo tính ổn định của Socket đa luồng.

| Giai đoạn | Khoa (Backend Server & Database) `feature/khoa-server-db` | Sự (Client UI & Packet Payload) `feature/tho-client-gui` |
| --- | --- | --- |
| **1. Nền tảng & Đăng nhập** | Thiết kế CSDL (bảng User, Message, Group). Viết các lớp DTO/DAO/BLL. Tích hợp API Email OTP. | Dùng Swing thiết kế form Đăng nhập/Đăng ký. Validate dữ liệu đầu vào. Tạo Socket Client kết nối cơ bản. |
| **2. Đa luồng & Chat 1-1** | Khởi tạo kiến trúc Server Multithreading quản lý danh sách Socket kết nối. Viết hàm truy xuất lịch sử chat. | Thiết kế giao diện Dashboard & Khung chat. Xử lý truyền/nhận tin nhắn text thô qua Socket và render lên UI. |
| **3. Mở rộng Nhóm & File** | Viết BLL xử lý logic tạo nhóm, quản lý thành viên. Tạo luồng stream nhận, lưu trữ file đính kèm/sticker. | Giao diện Popup tạo nhóm. Viết thuật toán phân mảnh gói dữ liệu (packet) để gửi file đính kèm dưới 1MB. |
| **4. Ghép Mã hóa (Bảo mật)** | Cài đặt thuật toán mã hóa bất đối xứng RSA (Sinh khóa / Cấp phát khóa phiên). | Đóng gói hàm `sendMessage`/`receiveMessage` qua class trung gian `SecurityUtils` (Mã hóa đối xứng AES). |
| **5. Trạng thái & Quản trị** | Xử lý logic Broadcast báo hiệu Online/Offline. Ghi Log hệ thống. Viết API Khóa tài khoản. | Thiết kế UI phân trang tin nhắn (tải tin cũ). Cập nhật trạng thái "đã gửi/nhận/xem". Chức năng chặn (Block). |

---

## 5. Quy chuẩn Giao tiếp Dữ liệu (Packet Concept)

Để thuận tiện cho việc "đắp" lớp mã hóa ở Giai đoạn 4, luồng dữ liệu truyền tải giữa Client và Server sẽ không truyền chuỗi tự do, mà được chuẩn hóa theo định dạng nhất quán (Ví dụ: Sử dụng JSON format hoặc String phân cách `[ACTION]|[SENDER]|[DATA]`) trước khi đẩy vào `DataOutputStream`.
