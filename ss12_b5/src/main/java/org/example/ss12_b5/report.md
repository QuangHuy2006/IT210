Phần 1 – Kịch bản lỗi (Scenario Analysis)
Trong quá trình phát triển ứng dụng Spring Boot quản lý bác sĩ, nếu không cấu hình đúng hoặc thiếu kiến thức từ Session 12, có thể gặp các lỗi sau:

Sai vị trí đặt ảnh đại diện (Avatar)

Nếu ảnh không đặt trong thư mục src/main/resources/static/images, khi hiển thị giao diện HTML sẽ không tìm thấy đường dẫn, dẫn đến lỗi 404 – Resource Not Found.

Ví dụ: <img src="/images/avatar1.jpg"> sẽ không hiển thị nếu file nằm sai thư mục.

Sai cấu hình Port hoặc Context Path

Nếu không đổi port trong application.properties, ứng dụng sẽ chạy mặc định ở 8080, dễ gây xung đột với các ứng dụng khác.

Nếu quên cấu hình server.servlet.context-path=/med-manager, URL sẽ không khớp với yêu cầu nghiệp vụ (ví dụ: HR truy cập http://localhost:8081/med-manager/doctors nhưng ứng dụng lại chạy ở http://localhost:8081/doctors).

Thiếu dependency trong build.gradle

Nếu quên thêm spring-boot-starter-web, ứng dụng sẽ không khởi động được Web Server (Tomcat embedded), dẫn đến lỗi ClassNotFoundException hoặc không thể render giao diện HTML.

Nếu thiếu dependency cho Thymeleaf (nếu dùng template engine), các file HTML sẽ không được xử lý đúng.
Phần 2:
```text
ss12_b5/
├── build.gradle
├── gradlew
├── settings.gradle
└── src/
    └── main/
    ├── java/
    │   └── com/example/ss12_b5/
    │       ├── controller/
    │       │   └── DoctorController.java
    │       └── model/
    │           └── Doctor.java
    └── resources/
    ├── static/
    │   └── images/
    │       └── avatar1.jpg
    ├── templates/
    │   ├── doctors.html
    │   └── add-doctor.html
    └── application.properties
```