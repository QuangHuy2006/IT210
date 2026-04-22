Phần 1 – Kịch bản lỗi với Lazy Loading
Lỗi thường gặp: Nếu bạn truy cập vào danh sách chi tiết thuốc (prescription.getDetails()) sau khi Session đã đóng, Hibernate sẽ ném ra LazyInitializationException.

Nguyên nhân: Với FetchType.LAZY, dữ liệu con (chi tiết thuốc) chỉ được tải khi cần. Nhưng nếu Session đã đóng, Hibernate không còn kết nối DB để truy vấn bổ sung → lỗi.

Cách khắc phục:

Mở rộng phạm vi Session (Open Session in View pattern): Giữ session mở đến khi view render xong.

Dùng JOIN FETCH trong HQL: Tải trước dữ liệu con ngay khi truy vấn cha.
```java
String hql = "FROM Prescription p JOIN FETCH p.details WHERE p.id = :id";
```
Chuyển sang FetchType.EAGER (không khuyến khích vì dễ gây tải thừa).

Dùng DTO: Truy vấn dữ liệu cần thiết rồi map sang DTO, tránh lazy load sau khi session đóng.