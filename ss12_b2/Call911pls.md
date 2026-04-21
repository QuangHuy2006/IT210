Vì sao Spring Boot không cần ghi version cho dependency?

Trong Spring Boot, bạn thường thấy:

```java

implementation 'org.springframework.boot:spring-boot-starter-validation'
```


- Không có version, nhưng vẫn chạy bình thường.

- Lý do chính: Spring Boot quản lý version hộ bạn

Spring Boot sử dụng:

- Dependency Management (BOM – Bill of Materials)

Cụ thể:

* Plugin org.springframework.boot
* Hoặc dependency spring-boot-dependencies

- Nó định nghĩa sẵn version tương thích cho:

* Spring Framework
* Jackson
* Hibernate
* Validation
* Logging
* …

⸻

- Lợi ích

- 1. Tránh xung đột version

Bạn không cần lo:

* thư viện A cần Jackson 2.15
* thư viện B cần Jackson 2.13

-️ Spring Boot đã chọn version tương thích
⸻

- 2. Đồng bộ toàn hệ sinh thái

Tất cả thư viện:

* chạy ổn định
* test sẵn với nhau

⸻

- 3. Viết gọn & dễ maintain

- Không cần:


```javaj

implementation 'org.hibernate.validator:hibernate-validator:8.0.1.Final'
```

✅ Chỉ cần:
```java
implementation 'org.springframework.boot:spring-boot-starter-validation'
```




 Bạn không cần ghi version vì:

* Spring Boot đã quản lý version tập trung
* Giúp ổn định + giảm lỗi + dễ nâng cấp

⸻


```java

dependencies {

    //  Web

    implementation 'org.springframework.boot:spring-boot-starter-web'

    //  Validation

    implementation 'org.springframework.boot:spring-boot-starter-validation'

    //  Lombok (compile only)

    compileOnly 'org.projectlombok:lombok'

    // ️ Lombok annotation processor

    annotationProcessor 'org.projectlombok:lombok'

    // (optional) test

    testImplementation 'org.springframework.boot:spring-boot-starter-test'

}


```