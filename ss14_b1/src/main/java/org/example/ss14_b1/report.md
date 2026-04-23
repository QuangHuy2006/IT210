Phần 1 – Phân tích logic
Trong đoạn code hiện tại:

Bạn mở Session nhưng không hề bắt đầu Transaction (session.beginTransaction()).

Khi gọi session.update(order), Hibernate sẽ ngay lập tức flush thay đổi xuống DB (tùy cấu hình), dẫn đến trạng thái đơn hàng được cập nhật thành "PAID".

Sau đó, hệ thống gặp lỗi RuntimeException trước khi trừ tiền trong ví. Vì không có Transaction bao bọc, không có cơ chế rollback để hoàn tác thay đổi đã ghi.

Kết quả: Đơn hàng đã đổi trạng thái, nhưng số dư ví vẫn nguyên → dữ liệu mất tính toàn vẹn.

Vòng đời Transaction bị thiếu:

beginTransaction() để bắt đầu giao dịch.

commit() để xác nhận thay đổi khi mọi thao tác thành công.

rollback() để hoàn tác khi có lỗi.