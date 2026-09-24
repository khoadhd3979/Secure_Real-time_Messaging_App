Dưới đây là bản phân tích yêu cầu và thiết kế dự án (README) dành cho đồ án Lập trình ứng dụng mạng của bạn, được tổng hợp từ các quy định chung và yêu cầu riêng của đề tài Chat, đồng thời kế thừa cấu trúc từ tệp README mẫu.

### 1. Tên Ứng dụng Đề xuất

**SecureChat-Java: Ứng dụng Nhắn tin Thời gian thực Bảo mật**

---

### 2. Viết README cho Dự án

# Đồ án: SecureChat-Java - Ứng dụng Nhắn tin Thời gian thực

> Hệ thống ứng dụng chat nhắn tin thời gian thực qua mạng, hỗ trợ đa luồng và mã hóa dữ liệu.
> **Mô hình:** Client - Server qua Socket TCP/UDP
> **Ngôn ngữ:** Java (Giao diện Swing)
> **Quy mô nhóm:** 2 thành viên
> 
> 

---

## 📑 Mục lục (Sắp xếp theo thứ tự quan trọng)

1. [1. Kiến trúc tổng thể & Công nghệ (⭐ Quan trọng nhất)](https://www.google.com/search?q=%25231-ki%25E1%25BA%25BFn-tr%25C3%25BAc-t%25E1%25BB%2595ng-th%25E1%25BB%2583--c%25C3%25B4ng-ngh%25E1%25BB%2587&utm_source=gemini)
2. [2. Luồng bảo mật & Mã hóa dữ liệu (⭐ Quan trọng nhất)](https://www.google.com/search?q=%25232-lu%25E1%25BB%2593ng-b%25E1%25BA%25A3o-m%25E1%25BA%25ADt--m%25C3%25A3-h%25C3%25B3a-d%25E1%25BB%25AF-li%25E1%25BB%2587u&utm_source=gemini)
3. [3. Thiết kế Cơ sở dữ liệu (CSDL) (⭐ Quan trọng)](https://www.google.com/search?q=%25233-thi%25E1%25BA%25BFt-k%25E1%25BA%25BF-c%25C6%25A1-s%25E1%25BB%259F-d%25E1%25BB%25AF-li%25E1%25BB%2587u-csdl&utm_source=gemini)
4. [4. Tính năng cốt lõi (Client & Server) (⭐ Quan trọng)](https://www.google.com/search?q=%25234-t%25C3%25ADnh-n%25C4%2583ng-c%25E1%25BB%2591t-l%25C3%25B5i-client--server&utm_source=gemini)
5. [5. Phân công công việc & Git Workflow (🔥 Thiết yếu cho nhóm)](https://www.google.com/search?q=%25235-ph%25C3%25A2n-c%25C3%25B4ng-c%25C3%25B4ng-vi%25E1%25BB%2587c--git-workflow&utm_source=gemini)
6. [6. Hướng dẫn cài đặt & Setup môi trường (🛠 Cần thiết)](https://www.google.com/search?q=%25236-h%25C6%25B0%25E1%25BB%259Bng-d%25E1%25BA%25ABn-c%25C3%25A0i-%25C4%2591%25E1%25BA%25B7t--setup-m%25C3%25B4i-tr%25C6%25B0%25E1%25BB%259Dng&utm_source=gemini)
7. [7. Lộ trình phát triển (📅 Quản lý tiến độ)](https://www.google.com/search?q=%25237-l%25E1%25BB%2599-tr%25C3%25ACnh-ph%25C3%25A1t-tri%25E1%25BB%2583n&utm_source=gemini)
8. [8. Tính năng nâng cao (Điểm cộng) (🌟 Mở rộng)](https://www.google.com/search?q=%25238-t%25C3%25ADnh-n%25C4%2583ng-n%25C3%25A2ng-cao-%25C4%2591i%25E1%25BB%2583m-c%25E1%25BB%2599ng&utm_source=gemini)

---

## 1. Kiến trúc tổng thể & Công nghệ

Dự án sử dụng kiến trúc **Client-Server** giao tiếp qua mạng (LAN hoặc Internet).

* **Ngôn ngữ & Giao diện:** Java, giao diện đồ họa (GUI) trực quan sử dụng Java Swing.


* **Xử lý mạng:** Socket TCP hoặc UDP.


* **Đa luồng (Multithreading):** Server bắt buộc xử lý đa luồng để phục vụ nhiều client cùng lúc.


* **Database:** SQL (Sử dụng kiến trúc DAO/BLL/DTO để quản lý truy vấn).
* **Xác thực:** Gửi mã OTP qua Email (thời hạn 10 phút) để kích hoạt tài khoản[cite: 2].

---

## 2. Luồng bảo mật & Mã hóa dữ liệu *(Mục bổ sung)*

Yêu cầu kỹ thuật bắt buộc của hệ thống là bảo mật kênh truyền và dữ liệu:

* **Bảo vệ mật khẩu:** Mật khẩu người dùng được lưu ở dạng hash (băm), tuyệt đối không lưu rỗng.


* **Mã hóa lai (Hybrid Encryption):**
* Sử dụng mã hóa bất đối xứng (vd: RSA) để trao đổi khóa phiên một cách an toàn.


* Sử dụng mã hóa đối xứng (vd: AES) để mã hóa toàn bộ nội dung tin nhắn và file đính kèm truyền qua lại giữa Client-Server.


* *Lưu ý:* Mã hóa áp dụng ở tầng kênh truyền, Server vẫn xử lý và lưu trữ dữ liệu bình thường sau khi giải mã.




* **Kiểm tra đầu vào:** Toàn bộ dữ liệu đầu vào trên GUI phải được validate (kiểm tra) để không phát sinh lỗi khi chạy.



---

## 3. Thiết kế Cơ sở dữ liệu (CSDL)

Hệ thống lưu trữ trên Server tối thiểu gồm các thực thể[cite: 2]:

* **Tài khoản người dùng:** Username (là địa chỉ email), password (hash), họ tên, giới tính, ngày sinh[cite: 2].
* **Nhóm & Thành viên:** Quản lý danh sách các nhóm chat và mapping thành viên trong nhóm[cite: 2].
* **Tin nhắn:** Lưu toàn bộ lịch sử tin nhắn 1-1, tin nhắn nhóm, file đính kèm, sticker[cite: 2].
* **Danh bạ & Trạng thái:** Danh sách bạn bè, danh sách bị chặn (block list)[cite: 2].
* **Nhật ký (Log):** Log hoạt động của hệ thống (đăng nhập/xuất, tạo nhóm, đăng ký)[cite: 2].

---

## 4. Tính năng cốt lõi (Client & Server)

### Chức năng phía Client (GUI)

* **Quản lý tài khoản:** Đăng ký, đăng nhập, cập nhật thông tin[cite: 2].
* **Nhắn tin thời gian thực:** Chat 1-1 và chat nhóm (tạo nhóm, thêm/mời/xóa thành viên bởi admin)[cite: 2].
* **File đính kèm:** Gửi file (<1MB) và sticker qua kênh truyền đã mã hóa[cite: 2].
* **Trạng thái tin nhắn:** Hiển thị trạng thái đã gửi / đã nhận / đã xem[cite: 2].
* **Danh sách bạn bè:** Lưu người đã trò chuyện, tự động thông báo online/offline khi trạng thái thay đổi[cite: 2].
* **Lịch sử:** Tải tin nhắn gần nhất khi đăng nhập, hỗ trợ phân trang để tải tin cũ[cite: 2].
* **Chặn (Block):** Chặn nhận tin nhắn từ người/nhóm cụ thể[cite: 2].

### Chức năng phía Server

* **Thống kê:** Thống kê tổng số người dùng và số lượng đang online[cite: 2].
* **Quản trị:** Khóa (block) người dùng bằng lệnh hoặc giao diện[cite: 2].
* **Broadcast:** Gửi thông báo hệ thống đến toàn bộ người dùng[cite: 2].

---

## 5. Phân công công việc & Git Workflow

Dự án được chia cho **2 thành viên** nhằm tối ưu việc quản lý database và giao diện.

### Thành viên 1: Khoa (Backend Server, Security & Database)

* **Nhiệm vụ:**
* Thiết kế CSDL SQL và xây dựng các lớp DTO/DAO/BLL.
* Khởi tạo kiến trúc Server đa luồng (Multithreading) quản lý các kết nối Socket.


* Cài đặt thuật toán Hash mật khẩu và mã hóa lai RSA/AES trao đổi khóa.


* Tích hợp API gửi Email chứa mã OTP (thời hạn 10 phút)[cite: 2].
* Xây dựng chức năng thống kê, ghi log và quản lý block trên Server[cite: 2].


* **Branch làm việc:** `feature/khoa-server-db`

### Thành viên 2: Thọ (Client UI, Logic xử lý & Packet payload)

* **Nhiệm vụ:**
* Thiết kế toàn bộ Client GUI bằng Java Swing.


* Xử lý logic luồng màn hình: Đăng ký/Đăng nhập -> Dashboard -> Khung chat 1-1/Nhóm[cite: 2].
* Xử lý chia nhỏ gói dữ liệu để gửi file đính kèm dưới 1MB qua Socket[cite: 2].
* Render trạng thái tin nhắn (đã gửi/nhận/xem) và xử lý UI phân trang lịch sử chat[cite: 2].
* Lắng nghe Broadcast từ Server để cập nhật trạng thái online/offline của danh sách bạn bè[cite: 2].


* **Branch làm việc:** `feature/tho-client-gui`

---

## 6. Hướng dẫn cài đặt & Setup môi trường

### Công cụ yêu cầu

* **Java JDK:** Phiên bản 17+
* **Database:** MySQL hoặc SQL Server.
* **IDE:** IntelliJ IDEA hoặc Eclipse (Khuyến khích sử dụng WindowBuilder nếu dùng Eclipse để kéo thả GUI).
* **Thư viện bổ sung:** `java.mail` (Gửi OTP Email), JDBC Driver (Kết nối SQL).

### Thiết lập môi trường

1. **Clone project:** `git clone <repo_url>`
2. **Chạy Database:** Import file `database/schema.sql` vào hệ quản trị CSDL của bạn. Cấu hình lại connection string trong thư mục DAO.
3. **Khởi động Server:** Chạy class `ServerMain.java`. Đảm bảo port (ví dụ: `8080`) đang được mở.
4. **Khởi động Client:** Chạy class `ClientMain.java`. Nhập IP của Server (dùng `localhost` hoặc `127.0.0.1` nếu test cục bộ) để kết nối.



---

## 7. Lộ trình phát triển

* **Tuần 1:** Setup cấu trúc thư mục, thiết kế UI Swing cơ bản, tạo CSDL, test kết nối Socket đơn luồng.
* **Tuần 2:** Khoa triển khai DAO/BLL và hệ thống mã hóa RSA/AES. Thọ gắn API Email OTP và hoàn thiện luồng đăng nhập[cite: 2].


* **Tuần 3:** Nâng cấp Server lên Multithreading. Triển khai nhắn tin 1-1 và nhắn tin nhóm thời gian thực, truyền file đính kèm[cite: 2].


* **Tuần 4:** Hoàn thiện trạng thái tin nhắn, phân trang, ghép tính năng Broadcast, fix bug và làm báo cáo. Thành viên báo cáo phải giải thích được code và sửa lỗi phát sinh ngay tại buổi bảo vệ.



---

## 8. Tính năng nâng cao (Điểm cộng)

Nếu dư thời gian, nhóm sẽ tích hợp các tính năng tính điểm cộng[cite: 2]:

* Báo hiệu "đang nhập..." (typing indicator).
* Thu hồi/xóa tin nhắn hai phía.
* Tìm kiếm trong lịch sử tin nhắn, ghim tin nhắn, thả cảm xúc (reaction).
* Thông báo đẩy khi có tin mới lúc cửa sổ chat không được focus.
* Triển khai Server thực tế ra VPS / Internet.