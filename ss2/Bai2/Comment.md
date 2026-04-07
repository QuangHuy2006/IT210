Vị trí <%! private int requestCounter = 0; %> - Loại lỗi: Race Condition: Biến được khai 
báo trong thẻ Declaration (<%! %>) sẽ trở thành biến instance (toàn cục) của Servlet. Vì JSP xử lý đa luồng,
khi nhiều người truy cập đồng thời, việc tăng giá trị biến không được đồng bộ hóa sẽ dẫn đến sai 
lệch số liệu và không an toàn về luồng (thread-unsafe).

Vị trí <%= sv.getScore()%>; %> - Loại lỗi: Syntax Error (Lỗi cú pháp hiển thị): 
Trong thẻ Expression (<%= %>), dấu chấm phẩy ; 
không được coi là kết thúc câu lệnh Java mà sẽ bị trình duyệt in trực tiếp ra HTML dưới dạng văn bản.
Điều này khiến điểm số hiển thị kèm ký tự thừa (ví dụ: 92;), gây mất thẩm mỹ và sai lệch dữ liệu trên giao diện.

Vị trí <%= sv.getFullName() %> - Loại lỗi: XSS (Cross-Site Scripting): 
Dữ liệu được in trực tiếp từ Java bean ra trang web mà không qua bộ lọc (escape).
Nếu dữ liệu tên sinh viên chứa mã độc như <script>alert(1)</script>, 
mã này sẽ thực thi ngay trên trình duyệt của người dùng, gây nguy cơ bị đánh cắp thông tin hoặc chiếm quyền điều khiển phiên làm việc.

Vị trí `` - Loại lỗi: Comment HTML không phù hợp: 
Sử dụng chú thích HTML cho các ghi chú nội bộ của lập trình viên sẽ khiến toàn bộ nội dung đó bị gửi về phía client.
Người dùng có thể xem được các ghi chú này khi kiểm tra mã nguồn (View Source),
làm tăng dung lượng file không cần thiết và có thể lộ bí mật thuật toán hoặc cấu trúc hệ thống.

Vị trí phần if-else xếp loại - Loại lỗi:
Logic in View (Vi phạm mô hình MVC): 
Việc tính toán xếp loại dựa trên điểm số thuộc về Logic nghiệp vụ (Business Logic).
Việc đưa các khối lệnh điều kiện phức tạp vào JSP làm file hiển thị trở nên rối rắm, 
khó bảo trì, khó viết Unit Test và vi phạm nguyên tắc tách biệt trách nhiệm giữa Controller và View.