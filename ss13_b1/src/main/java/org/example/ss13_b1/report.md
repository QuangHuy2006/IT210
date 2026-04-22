Phần 1 - Phân tích
Tại sao thiếu hibernate.dialect khiến Hibernate không khởi động?  
Hibernate cần biết loại cơ sở dữ liệu (MySQL, PostgreSQL, Oracle, v.v.) để dịch các câu lệnh HQL/Criteria thành SQL tương ứng. Nếu không có hibernate.dialect, Hibernate không thể xác định cú pháp SQL chuẩn cho DBMS, dẫn đến lỗi khi khởi tạo SessionFactory.

Thuộc tính giúp Hibernate tự động tạo bảng từ Java Class:  
Chính là hibernate.hbm2ddl.auto. Thuộc tính này cho phép Hibernate tự động sinh schema dựa trên entity. Các giá trị thường dùng:

create → tạo mới bảng mỗi lần chạy.

update → cập nhật schema theo entity.

create-drop → tạo bảng khi khởi động và xóa khi tắt.

validate → chỉ kiểm tra schema, không thay đổi.