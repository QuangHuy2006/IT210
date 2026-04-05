## 1. Cấu trúc Thư mục Dự án (Gradle)
Bạn thực hiện tạo các file theo cấu trúc sau trong IntelliJ để đảm bảo Spring có thể quét được các Bean:

```text
Session1/
└── src/
    └── main/
        ├── java/
        │   └── atmin.b5/
        │       ├── config/
        │       │   └── AppConfig.java      (File cấu hình quét Bean)
        │       ├── model/
        │       │   └── SystemConfig.java   (Lớp định nghĩa Bean)
        │       └── Main.java               (Hàm chạy chính)
        └── resources/                      (Thư mục để trống)
```