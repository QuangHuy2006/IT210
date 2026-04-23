# Phần 1 – Phân tích bài toán
Input (đầu vào):

Khách hàng nhấn nút “Mua ngay” cho sản phẩm iPhone 15.

Số lượng tồn kho hiện tại (ví dụ: 5 chiếc).

Thông tin khách hàng để tạo đơn hàng.

Output (kết quả mong đợi):

Nếu còn hàng: hệ thống tạo đơn hàng thành công và giảm tồn kho.

Nếu hết hàng: hệ thống trả về thông báo “Hết hàng”.

Không được phép xảy ra tình trạng bán vượt quá số lượng thực tế trong kho.

Vấn đề concurrency (song song):

Khi nhiều luồng cùng đọc tồn kho = 1, cả hai đều nghĩ rằng còn hàng và cùng trừ kho → dẫn đến tồn kho âm hoặc bán lố.

Đây là tình huống điển hình của race condition.

# Phần 2 – Đề xuất giải pháp
Để ngăn chặn việc bán lố, có hai hướng chính:

Isolation Level (Mức độ cô lập giao dịch):

Sử dụng mức cô lập cao hơn như SERIALIZABLE hoặc REPEATABLE READ để đảm bảo rằng khi một transaction đang đọc và cập nhật tồn kho, transaction khác không thể đọc giá trị cũ.

Nhược điểm: có thể gây giảm hiệu năng khi lượng truy cập cực lớn.

Locking trong Hibernate:

Pessimistic Locking: Khi đọc sản phẩm, dùng LockMode.PESSIMISTIC_WRITE để khóa bản ghi. Transaction khác phải chờ cho đến khi transaction hiện tại hoàn tất.

```java
Product product = session.get(Product.class, productId, LockMode.PESSIMISTIC_WRITE);
```
Optimistic Locking: Thêm trường @Version vào entity Product. Khi nhiều transaction cùng cập nhật, Hibernate sẽ phát hiện xung đột version và ném exception. Lúc đó bạn có thể retry hoặc báo “Hết hàng”.

```java
@Version
private int version;
```
Với tình huống Flash Sale (hàng nghìn người cùng mua), Pessimistic Locking thường an toàn hơn vì đảm bảo không bán lố, dù hiệu năng có thể giảm. Nếu muốn hiệu năng cao hơn, có thể dùng Optimistic Locking kết hợp cơ chế retry.

```text
[Khách hàng nhấn "Mua ngay"]
              |
              v
      [Bắt đầu Transaction]
              |
              v
   [Đọc tồn kho sản phẩm iPhone 15]
              |
   +----------+-----------+
   |                      |
   v                      v
[Stock >= 1]         [Stock < 1]
   |                      |
   v                      v
[Tạo Order mới]       [Thông báo "Hết hàng"]
   |                      
   v                      
[Trừ số lượng kho]         
   |                      
   v                      
[Commit Transaction]        
   |                      
   v                      
[Phản hồi "Đặt hàng thành công"]
```