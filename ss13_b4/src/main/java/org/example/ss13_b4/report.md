Phần 1 – Phân tích: HQL vs Native Query
Native Query (SQL thuần):

Truy vấn trực tiếp vào bảng và cột trong DB (SELECT * FROM medicines WHERE expiry_date < NOW()...).

Nếu đổi tên bảng hoặc cột trong DB, toàn bộ code Java sẽ lỗi vì query phụ thuộc vào tên vật lý.

Ưu điểm: linh hoạt, tận dụng cú pháp đặc thù của DB.

Nhược điểm: gắn chặt với schema, dễ lỗi khi DB thay đổi, khó bảo trì.

HQL (Hibernate Query Language):

Truy vấn dựa trên entity và thuộc tính trong class Java, không dựa vào tên bảng/cột.

Ví dụ: FROM Medicine m WHERE m.expiryDate < :today.

Nếu đổi tên cột trong DB, chỉ cần sửa mapping trong entity (@Column(name="...")), code HQL vẫn chạy bình thường.

Ưu điểm: an toàn hơn khi DB thay đổi, code gắn với mô hình đối tượng, dễ đọc và bảo trì.

Nhược điểm: không tận dụng được hết cú pháp đặc thù của DB (nhưng có thể fallback sang Native Query khi cần).

--> Kết luận: HQL giúp mã nguồn an toàn hơn vì tách biệt logic nghiệp vụ (entity) khỏi chi tiết vật lý của DB (tên bảng/cột).
