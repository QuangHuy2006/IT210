# BÁO CÁO PHÂN TÍCH VÀ THIẾT KẾ GIẢI PHÁP: ORDERFOODSERVICE

## 1. Xác định Dữ liệu Đầu vào (Input) và Kết quả Đầu ra (Output)

Để hệ thống hoạt động ổn định và xử lý được các tình huống nghiệp vụ, chúng ta cần định nghĩa cấu trúc dữ liệu như sau:

### **Dữ liệu đầu vào (Input)**

| Tham số             | Kiểu dữ liệu | Mô tả                                    |
|:--------------------|:-------------|:-----------------------------------------|
| `userId`            | String/Long  | Mã định danh của khách hàng đặt món.     |
| `foodName`          | String       | Tên món ăn khách chọn (VD: "Mì xào bò"). |
| `quantityRequested` | Integer      | Số lượng món ăn khách hàng muốn đặt.     |

### **Kết quả mong đợi (Output)**

* **Trường hợp Thành công:** Trả về một đối tượng `OrderResponse` (Trạng thái: Thành công, Thông báo: "Đặt món thành công", Số dư còn lại, Số lượng kho cập nhật).
* **Trường hợp Thất bại:** Trả về mã lỗi và thông báo chi tiết (VD: "Hết hàng", "Số dư tài khoản không đủ", hoặc "Tài khoản bị khóa do số dư âm").

---

## 2. Thiết kế Kiến trúc (Loose Coupling & DI)

Sử dụng nguyên lý **Dependency Injection** để tiêm các Repository vào Service thông qua `ApplicationContext`.

* **InventoryRepository**: Kiểm tra số lượng tồn kho.
* **UserAccountRepository**: Kiểm tra số dư và thực hiện trừ tiền.

---

## 3. Quy trình xử lý Logic

Hệ thống sẽ thực hiện theo các bước nghiêm ngặt sau để tránh các "bẫy" dữ liệu:

* **Bước 1:** Nhận yêu cầu đặt món từ `userId`.
* **Bước 2: Kiểm tra kho (Inventory Check)**
    * Truy vấn số lượng tồn kho của `foodName`.
    * **Xử lý bẫy:** Nếu khách gọi "Mì xào bò" nhưng số lượng kho trả về bằng **0** -> Báo lỗi "Hết hàng".
* **Bước 3: Kiểm tra tài khoản (Account Check)**
    * Truy vấn số dư tài khoản của `userId`.
    * **Xử lý bẫy:** Nếu số dư tài khoản bị **âm (< 0)** -> Báo lỗi "Tài khoản không hợp lệ".
    * Nếu số dư nhỏ hơn đơn giá món ăn -> Báo lỗi "Không đủ tiền".
* **Bước 4: Thực thi giao dịch**
    * Trừ số lượng tồn kho.
    * Trừ tiền tài khoản người dùng.
* **Bước 5:** Trả về kết quả thành công cho người dùng.

## 4. Yêu cầu Kiến trúc (Software Architecture)

Để đảm bảo hệ thống có tính linh hoạt cao và dễ bảo trì, giải pháp thiết kế tuân thủ các nguyên tắc sau:

* **Tách biệt thành phần (Separation of Concerns):** Phân chia rõ ràng trách nhiệm giữa tầng Service (xử lý nghiệp vụ) và tầng Repository (truy xuất dữ liệu).
* **Dependency Injection (DI):** `OrderFoodService` sẽ không tự khởi tạo các Repository. Thay vào đó, các Repository sẽ được "tiêm" (inject) vào Service thông qua `ApplicationContext`.
* **Loose Coupling (Liên kết lỏng lẻo):** * Service tương tác với Repository thông qua các **Interface**.
    * Điều này cho phép thay đổi logic lưu trữ (ví dụ: chuyển từ MySQL sang MongoDB) mà không cần sửa đổi mã nguồn của `OrderFoodService`.

---

## 5. Xử lý "Bẫy" dữ liệu (Data Trap Handling)

Đây là phần quan trọng nhất để đảm bảo tính toàn vẹn của hệ thống khi gặp các kịch bản dữ liệu bất thường từ Database:

### **5.1. Bẫy dữ liệu Kho (Inventory Trap)**
* **Tình huống:** Khách đặt món "Mì xào bò" nhưng `InventoryRepository` trả về số lượng là `0`.
* **Cơ chế xử lý:** * Hệ thống thực hiện kiểm tra điều kiện ngay sau khi nhận dữ liệu từ Repository.
    * Nếu `foodName == "Mì xào bò"` AND `stockQuantity == 0`:
        * Ngắt (abort) quy trình thanh toán.
        * Ném ra ngoại lệ (Exception): `OutOfStockException`.
        * Phản hồi Output: "Xin lỗi, món Mì xào bò đã hết hàng".

### **5.2. Bẫy dữ liệu Tài khoản (Account Balance Trap)**
* **Tình huống:** Cơ sở dữ liệu trả về số dư tài khoản khách hàng là số âm (do lỗi hệ thống cũ hoặc ghi nợ quá hạn).
* **Cơ chế xử lý:**
    * Trước khi thực hiện trừ tiền, hệ thống kiểm tra: `if (balance < 0)`.
    * Nếu điều kiện đúng:
        * Đánh dấu tài khoản không đủ điều kiện giao dịch.
        * Ném ra ngoại lệ: `InvalidAccountBalanceException`.
        * Phản hồi Output: "Giao dịch bị từ chối: Tài khoản đang có dư nợ âm".

---
