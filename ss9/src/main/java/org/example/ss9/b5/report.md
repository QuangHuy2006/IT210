Câu 1 – Kiến trúc & Logic
Luồng hoạt động (Data Flow):

Người dùng bấm nút chọn ngôn ngữ (?lang=ja hoặc ?lang=ko).

Spring Boot có LocaleChangeInterceptor sẽ “đánh chặn” URL, đọc tham số lang.

LocaleResolver sẽ ghi nhớ ngôn ngữ đã chọn (ở đây ta chọn CookieLocaleResolver).

Khi người dùng quay lại hôm sau, Cookie vẫn còn → hệ thống tự động hiển thị đúng ngôn ngữ.

Nếu người dùng nhập mã ngôn ngữ không hợp lệ (ví dụ ?lang=ar), Spring sẽ fallback về ngôn ngữ mặc định (English).

Quyết định Resolver:

SessionLocaleResolver: chỉ nhớ trong session, mất khi đóng trình duyệt → không đáp ứng yêu cầu số 2.

CookieLocaleResolver: ghi nhớ bằng cookie, tồn tại nhiều ngày → phù hợp yêu cầu “hôm sau mở lại vẫn nhớ”.

👉 Vì vậy ta chọn CookieLocaleResolver.

Câu 2 – Sản phẩm hoàn chỉnh

1. File từ điển .properties
   messages.properties (English – fallback):
```properties
hello=Hello
cart=Cart
promotion=Promotion
```
messages_ja.properties (Japanese):

```properties
hello=こんにちは
cart=カート
promotion=プロモーション
```
messages_ko.properties (Korean):

```properties
hello=안녕하세요
cart=장바구니
promotion=프로모션
```
Class configuration

```java

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver clr = new CookieLocaleResolver();
        clr.setDefaultLocale(Locale.ENGLISH); // fallback
        clr.setCookieName("lang");
        clr.setCookieMaxAge(30 * 24 * 60 * 60); // 30 ngày
        return clr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang"); // đọc ?lang=xx
        return interceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
```

Câu 3:

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <title>RikkeiMall</title>
</head>
<body>
<h1 th:text="#{hello}"></h1>
<p th:text="#{cart}"></p>
<p th:text="#{promotion}"></p>

<!-- Nút đổi ngôn ngữ -->
<a href="?lang=en">English</a> |
<a href="?lang=ja">日本語</a> |
<a href="?lang=ko">한국어</a>
</body>
</html>
```

Phần 3 – Kịch bản Test rủi ro
Người dùng nhập URL: http://localhost:8080/?lang=ar

LocaleChangeInterceptor đọc tham số lang=ar.

Vì không có file messages_ar.properties, Spring Boot tự động fallback về messages.properties (English).

Kết quả: Trang hiển thị "Hello", "Cart", "Promotion" bằng tiếng Anh.

Không có lỗi trắng trang, không bị sập.