| Giai đoạn phát triển | Nhiệm vụ của Khoa (Backend Server & Database) | Nhiệm vụ của Sự (Client GUI & Xử lý Socket) |
| --- | --- | --- |
| **1. Cấu trúc nền tảng & Đăng nhập** | - Thiết kế các bảng CSDL (tài khoản, tin nhắn, bạn bè, nhóm).<br>

<br>- Chú ý thiết lập giá trị mặc định (default) chặt chẽ cho các cột "trạng thái" để tránh lỗi truy vấn null khi lấy dữ liệu.<br>

<br>- Xây dựng toàn bộ kiến trúc DTO/DAO/BLL cho Server.<br>

<br>- Viết module tích hợp Java Mail để gửi mã OTP xác thực (10 phút). | - Dùng Java Swing thiết kế giao diện: Đăng nhập, Đăng ký, Nhập mã OTP.<br>

<br>- Viết các hàm kiểm tra (validate) định dạng email, độ dài mật khẩu trực tiếp trên UI.<br>

<br>- Tạo Socket Client kết nối đến Server và gửi/nhận tín hiệu đăng nhập cơ bản. |
| **2. Đa luồng, Mã hóa & Chat 1-1** | - Xây dựng Server đa luồng (Multithreading) quản lý danh sách các Socket Client đang kết nối.<br>

<br>- Cài đặt thuật toán sinh khóa RSA và tạo luồng cấp phát khóa cho Client.<br>

<br>- Viết các hàm DAO/BLL lưu và truy xuất lịch sử chat 1-1. | - Thiết kế giao diện Dashboard chính: Danh sách bạn bè và Khung chat 1-1.<br>

<br>- Viết logic mã hóa/giải mã đối xứng (AES) cho text trước khi đẩy qua kênh truyền Socket.<br>

<br>- Lắng nghe dữ liệu từ Server và render tin nhắn mới lên màn hình. |
| **3. Mở rộng Chat Nhóm & Xử lý File** | - Viết BLL xử lý nghiệp vụ tạo nhóm, cấp quyền admin, thêm/mời/xóa thành viên.<br>

<br>- Viết luồng stream nhận, mã hóa và lưu trữ file đính kèm/sticker từ Client.<br>

<br>- Ghi log toàn bộ hoạt động tạo nhóm, đăng nhập vào CSDL. | - Thiết kế Popup UI tạo nhóm chat, thêm thành viên.<br>

<br>- Cài đặt thuật toán chia nhỏ gói dữ liệu (packet phân mảnh) để gửi file đính kèm dưới 1MB qua mạng.<br>

<br>- Viết component hiển thị ảnh/sticker ngay trong luồng chat. |
| **4. Trạng thái, Phân trang & Quản trị** | - Xử lý logic Broadcast: Quét danh sách Socket để báo trạng thái Online/Offline cho bạn bè của user.<br>

<br>- Viết API nội bộ cập nhật trạng thái tin nhắn (đã gửi/nhận/xem).<br>

<br>- Tạo luồng xử lý khóa (block) tài khoản từ Server. | - Thiết kế nút "Tải thêm tin nhắn cũ" và xử lý UI phân trang lịch sử chat.<br>

<br>- Lắng nghe Broadcast để cập nhật chấm xanh (Online) trên danh sách bạn bè.<br>

<br>- Thêm nút chặn (Block) trên UI và vô hiệu hóa khung chat khi bị chặn. |

