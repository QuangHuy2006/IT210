Phần 1: Thiết kế kiến trúc (Architecture Design)
Các Entity chính
Wallet: Quản lý số dư của khách hàng.

Vendor: Nhà cung cấp sản phẩm.

Product: Sản phẩm, thuộc về một Vendor, có số lượng tồn kho.

Order: Đơn hàng tổng, chứa nhiều OrderDetail.

OrderDetail: Chi tiết đơn hàng, mỗi dòng gắn với một Product và Vendor.

Quan hệ
Vendor 1 - n Product

Order 1 - n OrderDetail

OrderDetail 1 - 1 Product

Wallet 1 - 1 Customer

Luồng dữ liệu (Data Flow)
Menu Console → Người dùng chọn chức năng.

Service Layer:

WalletService: Nạp tiền, kiểm tra số dư.

ProductService: Hiển thị danh sách sản phẩm & kho.

OrderService: Tạo đơn hàng đa Vendor.

PaymentService: Thực hiện thanh toán với Transaction Propagation.

DAO Layer: Tương tác với Database (CRUD).

Transaction Management:

Transaction tổng: REQUIRED.

Transaction con cho từng Vendor: REQUIRES_NEW.

Nếu một Vendor thất bại → rollback toàn bộ đơn hàng.

Phần 2: Phân tích rủi ro (Edge Cases)
Mất kết nối Database khi đang thanh toán  
→ Toàn bộ Transaction phải rollback, không trừ tiền khách hàng.

Sản phẩm bị xóa hoặc hết kho ngay lúc khách hàng nhấn mua  
→ Hệ thống phải kiểm tra lại kho trước khi commit.

Khách hàng nhập số âm khi nạp tiền  
→ Phải validate input, không cho phép số âm.

Khách hàng nhập chữ vào ô số (ví dụ: "abc" thay vì 1000)  
→ Phải bắt exception và hiển thị thông báo lỗi, không crash.

💻 Phần 3: Sản phẩm hoàn chỉnh
Giao diện Console (Menu)
Mã
```
===== RIKKEI MALL =====
1. Nạp tiền ví
2. Xem danh sách sản phẩm & kho
3. Thanh toán đơn hàng đa bên
4. Thống kê doanh thu
5. Thoát
   =======================
   Chọn chức năng:
```
   Trạng thái giao dịch
   Thành công: "Thanh toán thành công! Số dư ví còn lại: xxx"

Thất bại: "Thanh toán thất bại! Đơn hàng đã rollback."

Lỗi nhập liệu: "Vui lòng nhập số hợp lệ."

Code Structure (Java Spring Boot gợi ý)
model/Wallet.java, Product.java, Vendor.java, Order.java, OrderDetail.java

dao/WalletDAO.java, ProductDAO.java, OrderDAO.java

service/WalletService.java, ProductService.java, PaymentService.java

console/Menu.java (Main class điều hướng)

```text
src/
 └── main/java/com/rikkei/mall/
      ├── model/
      │    ├── Wallet.java
      │    ├── Vendor.java
      │    ├── Product.java
      │    ├── Order.java
      │    └── OrderDetail.java
      ├── dao/
      │    ├── WalletRepository.java
      │    ├── ProductRepository.java
      │    ├── OrderRepository.java
      │    └── VendorRepository.java
      ├── service/
      │    ├── WalletService.java
      │    ├── ProductService.java
      │    ├── OrderService.java
      │    └── PaymentService.java
      └── console/
           └── MenuConsole.java
```