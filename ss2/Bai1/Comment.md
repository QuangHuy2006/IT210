Lỗi trong MyWebAppInitializer.java:
Giải thích: Khi cấu hình getServletMappings() là "/api/*", 
bạn đang yêu cầu DispatcherServlet chỉ tiếp nhận và xử lý các request bắt đầu bằng tiền tố /api/ (ví dụ: /api/welcome, /api/user).

Hậu quả: Khi người dùng truy cập /welcome, 
Tomcat không tìm thấy Servlet nào khớp với URL này (vì nó không nằm trong vùng /api/*), dẫn đến lỗi 404 Not Found.

DispatcherServlet đang nghe: Chỉ những URL có dạng /demo/api/...

Lỗi trong WebConfig.java:

Hậu quả: @ComponentScan(basePackages = "com.demo.service") chỉ định cho Spring quét các Bean (như @Service, @Component) trong package service.

Hậu quả: Spring MVC sẽ tìm @Controller tại package com.demo.service, 
nhưng thực tế WelcomeController lại nằm ở package com.demo.controller. 
Do đó, Spring không tìm thấy và không khởi tạo Controller này vào Application Context.

Lỗi 500: Khi DispatcherServlet không tìm thấy Controller xử lý 
nhưng lại cố gắng xử lý theo luồng mặc định hoặc gặp lỗi cấu hình ViewResolver khi không có dữ liệu dẫn dắt, nó sẽ gây ra lỗi server.

Tổng hợp:
Trả lời: Ứng dụng KHÔNG chạy được.

Tại sao: Nếu chỉ sửa lỗi 1 (Servlet Mapping thành /), DispatcherServlet đã "nghe" được request /welcome.
Tuy nhiên, do lỗi 2 (ComponentScan), Spring không biết đến sự tồn tại của WelcomeController.
Kết quả là DispatcherServlet sẽ không tìm thấy handler nào để xử lý request đó, dẫn đến lỗi 404 (vì không có mapping cho URL) hoặc lỗi cấu hình hệ thống.