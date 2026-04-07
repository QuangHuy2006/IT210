Câu 1: Tại sao thông báo lỗi đăng nhập phải lưu vào Request Scope?

Lý do: Thông báo lỗi đăng nhập chỉ có giá trị ngay tại thời điểm xác thực thất bại và hiển thị lại trang login một lần duy nhất.

Hậu quả nếu lưu vào Session: Thông báo lỗi sẽ "dính" chặt vào phiên làm việc của người dùng. 
Nếu người dùng F5 (tải lại trang) hoặc sau đó đăng nhập thành công, thông báo lỗi cũ vẫn hiển thị chình chình trên màn hình, gây bối rối. 
Request Scope đảm bảo thông báo tự hủy ngay sau khi phản hồi (response) kết thúc.

Câu 2: Tại sao totalViewCount phải lưu vào Application Scope?

Lý do: Yêu cầu hệ thống là đếm tổng lượt xem trên toàn hệ thống (chia sẻ cho tất cả nhân viên). Application Scope là nơi duy nhất tồn tại xuyên suốt vòng đời của ứng dụng và dùng chung cho mọi người dùng.

Nếu lưu vào Session Scope: Mỗi nhân viên sẽ có một bộ đếm riêng. Nhân viên A xem 3 lần thấy số 3, nhân viên B mới vào xem 1 lần thấy số 1.
Họ sẽ không bao giờ biết được tổng cộng toàn cửa hàng đã xem bao nhiêu lần.

Câu 3: Race Condition và đoạn code tiềm ẩn nguy cơ

Race Condition là gì: 
Là tình trạng xảy ra khi nhiều luồng (nhiều nhân viên) cùng truy cập và sửa đổi một dữ liệu dùng chung (totalViewCount) tại cùng một thời điểm.


Vấn đề của đoạn code:

Luồng 1 đọc count = 5.
Luồng 2 đọc count = 5.
Luồng 1 tăng lên 6 và set lại.
Luồng 2 cũng tăng lên 6 và set lại.
Kết quả: Lẽ ra phải là 7, nhưng hệ thống chỉ ghi nhận 6.

Cách phòng tránh: Sử dụng từ khóa synchronized để khóa khối code xử lý biến đếm hoặc sử dụng AtomicInteger để đảm bảo tính nguyên tử (atomic) khi tăng giá trị.