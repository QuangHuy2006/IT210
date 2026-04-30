Phân tích
Phần 1 – Vì sao UPDATE không chạy?

Trong Spring Data JPA:
@Query mặc định được hiểu là SELECT query
Câu lệnh UPDATE / DELETE muốn thực thi phải khai báo rõ là query thay đổi dữ liệu

Code hiện tại:
@Query("UPDATE Order o SET o.status = 'CANCELLED' WHERE o.id = :orderId")
void cancelFraudulentOrder(@Param("orderId") Long orderId);

Thiếu 2 thứ quan trọng:
@Modifying --> để báo đây là câu lệnh UPDATE
@Transactional --> để đảm bảo có transaction commit xuống DB

==> Vì thiếu nên:
Query không được thực thi
Không lỗi compile
UI vẫn báo thành công (do không có exception)


Phần 3:
Đề xuất thêm cách xử lý "bẫy dữ liệu" (Edge case):
1. nếu Admin cố tình truyền vào một orderId là số âm
- Service xử lý:
if (orderId == null || orderId <= 0) {
throw new IllegalArgumentException("OrderId không hợp lệ");
}

        --> trả về phản hổi
                - HTTP 400 (Bad Request)
                - Message: "OrderId không hợp lệ"

    2. hoặc truyền vào mã của một đơn hàng đã ở trạng thái DELIVERED (Đã giao thành công)

        - Kiểm tra trước khi update
          Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Không tìm thấy đơn"));

          if (order.getStatus().equals("DELIVERED")) {
              throw new IllegalStateException("Không thể hủy đơn đã giao");
          }