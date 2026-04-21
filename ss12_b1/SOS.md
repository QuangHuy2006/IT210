Vì sao spring-boot-starter-web được gọi là “Starter”?

Trong Spring Boot, “Starter” là một gói dependency tổng hợp giúp bạn không cần khai báo từng thư viện nhỏ lẻ nữa.

 spring-boot-starter-web thực chất là một dependency “cha” kéo theo hàng loạt thư viện cần thiết cho ứng dụng web.

Nó đã bao gồm những gì?

- Spring Boot tự động thêm:

* - Spring MVC (xử lý REST API)
* - Embedded Tomcat (server chạy sẵn)
* - Jackson (convert JSON)
* - Validation API
* - Logging (Logback + SLF4J)


Nó gom toàn bộ dependency web cần thiết vào 1 gói duy nhất, giúp bạn:

* Không cần nhớ từng thư viện
* Không cần cấu hình thủ công
* Khởi chạy nhanh