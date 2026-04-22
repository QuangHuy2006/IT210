Phần 1 – Phân tích
Thiếu CascadeType.ALL gây lỗi:  
Khi bạn gọi session.save(prescription), Hibernate chỉ lưu đối tượng Prescription. Nếu danh sách details chưa được cascade, các PrescriptionDetail liên quan sẽ không được tự động lưu. Điều này dẫn đến lỗi TransientObjectException hoặc org.hibernate.PersistentObjectException vì Hibernate phát hiện có entity con chưa được quản lý (chưa có ID, chưa được gắn vào session). CascadeType.ALL (hoặc ít nhất CascadeType.PERSIST và CascadeType.REMOVE) đảm bảo rằng khi thao tác với entity cha, các entity con cũng được lưu/xóa đồng bộ.

Tại sao nên dùng FetchType.LAZY cho danh sách chi tiết thuốc:  
Nếu dùng FetchType.EAGER, mỗi lần truy vấn Prescription Hibernate sẽ tự động tải toàn bộ danh sách details, kể cả khi bạn không cần. Điều này gây ra overhead lớn, đặc biệt khi danh sách chi tiết dài. FetchType.LAZY chỉ tải dữ liệu khi bạn thực sự gọi getDetails(), giúp tối ưu hiệu năng và giảm tải cho DB.

# Flow:
``` text
session.save(prescription)
        │
        ├── Hibernate INSERT Prescription → tạo bản ghi trong bảng prescriptions
        │
        └── CascadeType.ALL kích hoạt
                └── duyệt danh sách details
                        ├── gán prescription_id = id của Prescription vừa tạo
                        └── INSERT vào bảng prescriptiondetail
```