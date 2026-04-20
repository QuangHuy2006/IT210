Câu 1: 
Công cụ cần dùng:  
- Cookie là lựa chọn đúng. Vì Cookie được lưu trực tiếp trên máy khách, có thể tồn tại nhiều ngày (ví dụ 30 ngày) kể cả khi tắt trình duyệt hay máy tính.
- Không dùng Session vì Session chỉ tồn tại trong vòng đời của trình duyệt. Khi người dùng đóng trình duyệt, Session sẽ mất, không đáp ứng yêu cầu Marketing.

Chặn bẫy dữ liệu XSS:  
Hacker có thể lợi dụng document.cookie để đánh cắp hoặc sửa cookie. Giải pháp:

Đặt cờ HttpOnly cho cookie → ngăn JavaScript truy cập.

Đặt cờ Secure -> chỉ gửi cookie qua HTTPS.

Đặt cờ SameSite -> hạn chế cookie bị gửi kèm trong request cross-site.

Kết hợp với việc lọc input và escape output để giảm nguy cơ XSS.

Câu 2:
```java
@PostMapping("/change-theme")
public ResponseEntity<String> changeTheme(@RequestParam("mode") String mode, HttpServletResponse response) {
    // Tạo cookie lưu theme
    Cookie themeCookie = new Cookie("theme_mode", mode);
    themeCookie.setMaxAge(30 * 24 * 60 * 60);
    themeCookie.setHttpOnly(true); 
    themeCookie.setSecure(true);  
    themeCookie.setPath("/");
    themeCookie.setSameSite("Strict");

    response.addCookie(themeCookie);

    return ResponseEntity.ok("Theme changed to " + mode);
}
```