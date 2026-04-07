Câu 1: XSS là gì? Tại sao <c:out> lại an toàn?

XSS (Cross-Site Scripting): Là lỗ hổng cho phép kẻ tấn công chèn các đoạn mã script độc hại (thường là JavaScript) vào trang web.
Khi người dùng khác xem trang, trình duyệt sẽ thực thi đoạn mã này.

Sự khác biệt: * ${keyword}: In trực tiếp chuỗi ra HTML. Nếu input là <script>alert(1)</script>, trình duyệt sẽ thực thi nó và hiện popup.

<c:out value="${keyword}"/>: Tự động thực hiện XML/HTML Escaping.

So sánh Output:

Input: <script>alert(1)</script>

Output của ${keyword}: <script>alert(1)</script> (Trình duyệt chạy script).

Output của <c:out>: <script>alert(1)</script> (Trình duyệt hiển thị dưới dạng văn bản thuần túy).

Câu 2: So sánh <c:if> và <c:choose>

<c:if>: Dùng cho các điều kiện đơn lẻ (đúng hoặc không làm gì cả).

<c:choose>: Hoạt động giống như switch-case hoặc if-else if-else, dùng khi có từ 3 nhánh điều kiện trở lên.

Áp dụng: Trong bài này, phần "Vé còn lại" nên dùng <c:choose> vì có 3 trạng thái rõ rệt (Hết vé, Sắp hết, Bình thường). 
Phần "Giá vé" có thể dùng <c:if> cho trường hợp Miễn phí và định dạng số cho trường hợp còn lại.

Câu 3: Tại sao dùng <c:url> thay vì hardcode?

Vấn đề: Hardcode href="/events/1/book" giả định ứng dụng chạy ở root /. Nếu deploy với context path /ticketing, link sẽ bị sai (dẫn đến 404).

Giải pháp: <c:url> tự động chèn Context Path của ứng dụng vào trước đường dẫn.

Nếu context là /ticketing, <c:url value="/events/1/book"/> sẽ sinh ra: /ticketing/events/1/book. 
Ngoài ra, nó còn hỗ trợ tự động đính kèm jsessionid nếu trình duyệt tắt Cookie