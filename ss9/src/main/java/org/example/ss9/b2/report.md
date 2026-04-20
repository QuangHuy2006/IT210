Câu 1:
Trong code gốc, tham số @CookieValue("guest_name") String guestName bắt buộc Spring Boot phải tìm thấy cookie guest_name trong request. Nếu cookie không tồn tại (trường hợp khách mới tinh), Spring Boot sẽ ngay lập tức ném ra MissingRequestCookieException và trả về lỗi 400.

Vì exception xảy ra trước khi hàm được thực thi, đoạn if (guestName == null) bên trong hàm không bao giờ chạy tới.

Nói cách khác, logic kiểm tra null ở trong hàm là vô dụng, vì Spring Boot không cho phép hàm chạy nếu cookie chưa có.

Nguyên nhân: do cách khai báo @CookieValue mặc định là required = true.

Câu 2:
Để tránh lỗi 400 và cho phép khách mới vào trang chủ, ta cần:

Đặt required = false để Spring Boot không bắt buộc cookie phải tồn tại.

Dùng defaultValue = "Khách lạ" để tự động gán giá trị mặc định khi cookie chưa có.
```java
@GetMapping("/home")
public String HomePage(@CookieValue(value = "guest_name", required = false, defaultValue = "Khách lạ") String guestName,
                       Model model) {
    if ("Khách lạ".equals(guestName)) {
        model.addAttribute("msg", "Chào khách lạ!");
    } else {
        model.addAttribute("msg", "Chào mừng " + guestName + " trở lại!");
    }
    return "home-page";
}

```