Báo cáo phân tích 3.1.
Giải thích về "Bẫy dữ liệu ép kiểu" Khi nhân viên nhập /bai5/orders/t, 
Spring sẽ cố gắng ép kiểu chuỗi "t" sang kiểu Long của biến id. 
Quá trình này thất bại và ném ra MethodArgumentTypeMismatchException. 
Nhờ có Annotation @ExceptionHandler, chúng ta đã chặn đứng việc hệ thống hiện trang lỗi 400 "đỏ ngòm" của Tomcat,
thay vào đó trả về một dòng thông báo tiếng Việt thân thiện, 
giúp nhân viên biết mình gõ sai mà không làm "sập" trải nghiệm ứng dụng.



3.2. Tính "Idempotent" (Tính lặp lại) Trong thiết kế HTTP:

DELETE là một phương thức Idempotent: Khi nhân viên bấm "Hủy" 3 lần, 
lần đầu tiên đơn hàng sẽ bị xóa. Lần thứ 2 và thứ 3, dù Request vẫn gửi đi,
nhưng kết quả cuối cùng (trạng thái hệ thống) vẫn là đơn hàng đó đã biến mất.
Nó không gây ra tác dụng phụ nguy hiểm.

POST KHÔNG phải là Idempotent: 
Mỗi lần bấm "Lưu Đơn" là một yêu cầu tạo mới tài nguyên. 
Nếu bấm 3 lần do lag, Server sẽ nhận 3 yêu cầu khác nhau và tạo ra 3 bản ghi đơn hàng giống hệt nhau trong Database.
Điều này dẫn đến việc thất thoát dữ liệu và tạo ra "đơn hàng rác".