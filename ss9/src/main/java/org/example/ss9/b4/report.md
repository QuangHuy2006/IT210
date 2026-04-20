Câu 1:
Giải pháp 1: Dùng <input type="hidden"> (Client-side)

Cách làm: Mỗi lần chuyển bước, ta render lại form và nhúng dữ liệu đã nhập ở bước trước vào các thẻ <input type="hidden">.

Ưu điểm: Không chiếm RAM server, vì dữ liệu được giữ ở phía client.

Nhược điểm: Dữ liệu nằm trên client, dễ bị người dùng chỉnh sửa hoặc hacker can thiệp. Cần validate kỹ khi lưu DB.

Giải pháp 2: Dùng @SessionAttributes hoặc HttpSession (Server-side)

Cách làm: Lưu dữ liệu tạm thời vào session trong suốt quá trình điền form. Đến bước 3 mới lấy toàn bộ dữ liệu từ session để ghi DB.

Ưu điểm: Dữ liệu an toàn hơn, không dễ bị chỉnh sửa từ client.

Nhược điểm: Nếu hàng ngàn người bỏ dở, session rác sẽ tồn tại trong RAM server → nguy cơ tràn bộ nhớ.

Cách khắc phục: Đặt timeout ngắn cho session, hoặc dùng cơ chế cleanup, hoặc lưu tạm vào DB với trạng thái “draft” thay vì giữ trong RAM.

Câu 2:
Giải pháp 1 – Hidden Input (Client-side)
```html
<form action="/submitInfor" method="post">
    <input type="text" name="fullName" />
    <input type="text" name="email" />
    <button type="submit">Tiếp</button>
</form>

<form action="/submitInfor" method="post">
    <input type="hidden" name="fullName" value="${fullName}" />
    <input type="hidden" name="email" value="${email}" />

    <input type="text" name="shopName" />
    <input type="text" name="shopAddress" />
    <button type="submit">Tiếp</button>
</form>
```
Giải pháp 2 – SessionAttributes (Server-side)
```java
@Controller
@SessionAttributes({"personalInfo", "shopInfo"})
public class ShopRegistrationController {

    @PostMapping("/submitPersonalInfo")
    public String submitUserInfo(@ModelAttribute PersonalInfo personalInfo, Model model) {
        model.addAttribute("personalInfo", personalInfo);
        return "add-user-form";
    }

    @PostMapping("/submitShopInfo")
    public String submitShopInfo(@ModelAttribute ShopInfo shopInfo, Model model) {
        model.addAttribute("shopInfo", shopInfo);
        return "add-shop-form";
    }

    @PostMapping("/submitInfo")
    public String submitInfo(@SessionAttribute("personalInfo") PersonalInfo personalInfo,
                        @SessionAttribute("shopInfo") ShopInfo shopInfo) {
        ShopRegistration registration = new ShopRegistration(personalInfo, shopInfo);
        registrationRepository.save(registration);
        return "add-info";
    }
}
```