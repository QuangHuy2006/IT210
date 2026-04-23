Phần 1 – Phân tích I/O
Input (đầu vào):

Khách hàng thêm sản phẩm vào giỏ và nhấn “Checkout”.

Thông tin sản phẩm, số lượng cần mua.

Thời gian giữ hàng (15 phút).

Output (kết quả mong đợi):

Nếu thanh toán thành công trong 15 phút → đơn hàng hoàn tất, trừ kho vĩnh viễn.

Nếu quá 15 phút không thanh toán hoặc hủy đơn → hệ thống tự động hoàn kho.

Không được để xảy ra tình trạng “treo” transaction lâu gây deadlock.

Phần 2 – Đề xuất giải pháp
Giải pháp 1: Transaction + trạng thái Pending

Khi khách hàng checkout, tạo đơn hàng với trạng thái PENDING.

Ngay lập tức trừ kho (reserve).

Nếu thanh toán thành công trong 15 phút → đổi trạng thái thành PAID.

Nếu hết hạn hoặc hủy → rollback bằng cách cộng lại kho và đổi trạng thái thành CANCELLED.

Ưu điểm: logic rõ ràng, dễ kiểm soát bằng transaction.

Nhược điểm: cần cơ chế theo dõi thời gian hết hạn.

Giải pháp 2: Scheduled Task / Interceptor

Khi checkout, vẫn tạo đơn hàng trạng thái PENDING và trừ kho.

Dùng Scheduled Task (ví dụ Spring Scheduler hoặc Quartz) chạy định kỳ để quét các đơn hàng PENDING quá 15 phút.

Nếu quá hạn → tự động hoàn kho và đổi trạng thái đơn hàng.

Ưu điểm: không giữ transaction lâu, tránh deadlock.

Nhược điểm: cần thêm cơ chế background job, phức tạp hơn.

Phần 3 – So sánh
Tiêu chí	Giải pháp 1: Transaction Pending	Giải pháp 2: Scheduled Task
Tốc độ xử lý	Nhanh, trực tiếp trong transaction	Nhanh, nhưng phụ thuộc job scheduler
Độ an toàn dữ liệu	Cao, vì rollback ngay trong transaction	Cao, nhưng cần đảm bảo job chạy đúng
Tài nguyên bộ nhớ	Có thể tốn nếu giữ transaction lâu	Ít tốn, vì job chạy ngắn gọn
Độ phức tạp code	Đơn giản, dễ hiểu	Phức tạp hơn, cần scheduler


Lựa chọn khuyến nghị: Giải pháp 2 (Scheduled Task) phù hợp hơn cho hệ thống thương mại điện tử có lượng truy cập cao, vì tránh được long-running transaction và deadlock.