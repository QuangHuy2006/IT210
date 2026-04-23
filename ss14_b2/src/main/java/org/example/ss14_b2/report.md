Phần 1 – Phân tích
Trong code hiện tại:

Bạn mở Session nhưng không khởi tạo Transaction (beginTransaction()).

Khi gọi session.update(order), Hibernate có thể flush thay đổi xuống DB ngay (tùy cấu hình). Vì không có transaction bao bọc, thay đổi này được ghi nhận độc lập.

Nếu bước “Hoàn kho” bị lỗi (ví dụ order.getProductId() trả về null), exception xảy ra nhưng trạng thái đơn hàng đã bị đổi thành "CANCELLED" và không thể rollback.

Hậu quả: đơn hàng bị hủy trên hệ thống, nhưng kho không được cộng lại số lượng sản phẩm. Điều này gây mất cân bằng tồn kho: hàng hóa biến mất khỏi hệ thống mà thực tế vẫn còn trong kho.

Thiệt hại: dữ liệu kho sai lệch, dẫn đến báo cáo tồn kho không chính xác, có thể gây thiếu hàng giả tạo hoặc thất thoát.