Code mẫu theo bài:
```java
public class RestaurantProfile {
    private String name;
    private String phone;
    private boolean active;

    // Đã có đầy đủ Getter/Setter
}

//Controller
@PostMapping("merchant/update-profile")
public String updateProfile(RestaurantProfile profile){
    System.out.println("Tên quán: " + profile.getName());
    // Gọi Service lưu vào DB...
    return "success";
}
```
```html
<form action="/merchant/update-profile" method="POST">
    <label>Tên cửa hàng:</label>
    <input type="text" name="restaurantName" />
    
    <label>Số điện thoại:</label>
    <input type="text" name="phone" />
    
    <label>Dạng mở cửa:</label>
    <input type="checkbox" name="active" value="MỞ CỬA" />
    
    <button type="submit">Lưu thông tin</button>
</form>
```
Trong HTML form, trường nhập tên có thuộc tính name="restaurantName".

Trong class RestaurantProfile, thuộc tính tương ứng là private String name;.

Spring Data Binding ánh xạ giá trị từ request parameter (name trong thẻ <input>) vào tên thuộc tính (name trong POJO) theo đúng tên.

Vì restaurantName ≠ name, Spring không tìm thấy parameter nào khớp với name, do đó profile.getName() trả về null.

Tại sao checkbox active (kiểu boolean) không hoạt động đúng?
HTML checkbox chỉ gửi dữ liệu lên server khi được tick. Khi tick, nó gửi cặp name=value với value được định nghĩa trong thẻ <input type="checkbox" name="active" value="MỞ CỬA" />.

Giá trị gửi lên lúc này là chuỗi "MỞ CỬA".

Spring khi binding vào boolean active cần giá trị "true" (hoặc "on", "yes", "1") để hiểu là true. "MỞ CỬA" không được coi là true → binding thất bại, giá trị mặc định false (hoặc null nếu là Boolean).

Ngoài ra, nếu checkbox không được tick, trình duyệt không gửi trường active nào → Spring giữ giá trị mặc định của boolean là false. Nhưng vấn đề chính vẫn là giá trị value không đúng định dạng boolean.