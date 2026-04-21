Vì sao nên dùng application.properties thay vì hard-code?

Trong Spring Boot, file application.properties được dùng để tách cấu hình ra khỏi code.

Vấn đề của hard-code
VD:

```java

String hospitalName = "Bệnh viện Đa khoa ABC";

```

Khi thay đổi:

* tên bệnh viện
* hotline
* IP database

Bạn phải sửa:

* nhiều file
* dễ sót
* dễ gây bug

Lợi ích khi dùng application.properties

1.  Dễ thay đổi (không cần sửa code)

Chỉ cần sửa:


```properties
hospital.name=Bệnh viện XYZ
```

- không cần build lại code logic

Tách biệt cấu hình & logic

* Code = xử lý nghiệp vụ
* Properties = cấu hình

 Code sạch hơn, dễ đọc hơn




3.  Hỗ trợ nhiều môi trường

Bạn có thể có:

* application-dev.properties
* application-prod.properties

=> Mỗi môi trường config khác nhau

⸻

4.  Bảo mật tốt hơn

Các thông tin như:

* DB IP
* API key

 Không nằm trong source code

⸻

 Dùng application.properties giúp:

* linh hoạt
* dễ maintain
* giảm lỗi khi thay đổi

⸻

application.properties

```properties
    hospital.name=Benh vien Da khoa ABC
    hospital.hotline=19001234
    hospital.db.ip=192.168.1.10
```


```java

package com.example.hospital.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class HospitalConfig {

    @Value("${hospital.name}")
    private String name;

    @Value("${hospital.hotline}")
    private String hotline;

    @Value("${hospital.db.ip}")
    private String dbIp;

    @PostConstruct
    public void init() {
        System.out.println("===== HOSPITAL CONFIG =====");
        System.out.println("Name: " + name);
        System.out.println("Hotline: " + hotline);
        System.out.println("DB IP: " + dbIp);
    }
}


```



@Component

 Để Spring quản lý class này (Bean)

⸻

 @PostConstruct

 Chạy sau khi Bean được khởi tạo

️ Perfect để in log khi app start








