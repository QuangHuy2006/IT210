1. Vấn đề:
   Hệ thống hiện tại khi tra cứu lịch sử đơn hàng:
   Trả về toàn bộ dữ liệu --> gây chậm, đơ giao diện
   Không hỗ trợ lọc theo trạng thái
   Không có phân trang
   Không có sắp xếp

   --> Trải nghiệm người dùng kém, khó tìm kiếm dữ liệu

2. Yêu cầu
    - Lọc (Filtering)
        + Theo status (PROCESSING, DELIVERED, CANCELLED, …)
    - Phân trang (Pagination)
        + Giới hạn số đơn mỗi trang
        + Có tổng số trang
    - Sắp xếp (Sorting)
        + Theo createdDate
        + Theo totalAmount
        + ASC / DESC

3. Các bẫy dữ liệu (Edge Cases)
    - Tràn viền phân trang
    + page < 0 → gán lại = 0
    + page > totalPages --> gán lại = trang cuối
      ==> Tránh lỗi 500

    - Tham số sort không hợp lệ, kiểu ?sortBy=abcxyz
      --> giải pháp:
      + Validate danh sách cho phép:

            + List.of("createdDate", "totalAmount")
                Nếu sai -->  dùng mặc định (createdDate)

    - Tham số filter đặc biệt
      status = ALL -> không lọc