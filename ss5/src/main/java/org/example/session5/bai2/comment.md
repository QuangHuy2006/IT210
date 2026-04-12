Cấu trúc đối tượng Dish Đối tượng Dish được thiết kế theo chuẩn POJO để 
mang dữ liệu từ Service qua Controller đến View.


id (Long/Int): Định danh duy nhất.

name (String): Tên món ăn.

price (Double): Giá tiền.

isAvailable (boolean): Trạng thái (true: Còn hàng, false: Hết hàng).

Sơ đồ luồng I/O (Input/Output) Dưới đây là cách dữ liệu di chuyển trong hệ thống:


Input: Client gửi request đến URL /bai2/dishes.

Processing: * DishController gọi DishService để lấy danh sách List.

Controller dùng Model.addAttribute("dishes", list) để đẩy dữ liệu vào "chiếc giỏ" chung.

Output: * Thymeleaf nhận file dish-list.html.

Thymeleaf duyệt qua danh sách dishes để render mã HTML động trả về trình duyệt.