# Bảng so sánh chi tiết các phương pháp Injection trong Spring

| Tiêu chí | Field Injection (@Autowired trên biến) | Constructor Injection (Tiêm qua hàm tạo) |
|----------|----------------------------------------|-------------------------------------------|
| **Cú pháp** | Ngắn gọn nhất. | Dài hơn một chút. |
| **Tính đóng gói** | Kém. Vi phạm tính đóng gói vì Spring can thiệp trực tiếp vào biến private. | Tốt. Đảm bảo tính đóng gói (Encapsulation). |
| **Tính bất biến** | Không thể dùng từ khóa final. Biến có thể bị thay đổi sau khi khởi tạo. | Tuyệt vời. Có thể dùng final, đảm bảo Dependency không bị thay đổi suốt vòng đời. |
| **Unit Test** | Khó. Phải dùng Reflection hoặc khởi chạy Spring Context để inject. | Dễ. Có thể tự tay truyền "đồ giả" (Mock) vào qua hàm tạo mà không cần Spring. |
| **Tính an toàn** | Dễ gặp lỗi NullPointerException nếu quên khởi tạo Spring. | Đảm bảo đối tượng luôn "sẵn sàng" ngay khi được tạo ra. |

## Kết luận

**Constructor Injection** được khuyến nghị là phương pháp tốt nhất vì:
- Đảm bảo tính đóng gói và bất biến
- Dễ dàng kiểm thử
- An toàn hơn trong việc quản lý dependency
- Đối tượng luôn ở trạng thái sẵn sàng sau khi khởi tạo
