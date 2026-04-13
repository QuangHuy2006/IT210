# DishController.java

```java
@Controller
@RequestMapping("/merchant/dish")
public class DishController {

    // API 1: Mở form thêm mới
    @GetMapping("/add")
    public String showAddForm(Model model) {
        // Lấy danh sách nhóm món ăn
        List<String> categories = Arrays.asList("Món chính", "Đồ uống", "Tráng miệng", "Topping");
        model.addAttribute("categories", categories);

        model.addAttribute("dish", new Dish());
        return "dish-add";
    }

    // API 2: Mở form chỉnh sửa
    @GetMapping("/edit")
    public String showEditForm(Model model) {
        // Lại phải lặp lại đoạn code lấy danh sách
        List<String> categories = Arrays.asList("Món chính", "Đồ uống", "Tráng miệng", "Topping");
        model.addAttribute("categories", categories);

        model.addAttribute("dish", new Dish("Trà sữa", "Đồ uống"));
        return "dish-edit";
    }

    // API 3: Mở trang tìm kiếm
    @GetMapping("/search")
    public String showSearchPage(Model model) {
        // Lại lặp lại lần thứ 3...
        List<String> categories = Arrays.asList("Món chính", "Đồ uống", "Tráng miệng", "Topping");
        model.addAttribute("categories", categories);

        return "dish-search";
    }
}
```
Việc lặp lại đoạn code model.addAttribute("categories", ...) ở cả 3 hàm GET đang vi phạm nguyên tắc DRY (Don't Repeat Yourself) – một nguyên tắc nổi tiếng trong lập trình nhằm tránh trùng lặp logic.

Rủi ro bảo trì:

Nếu sau này danh sách nhóm món ăn thay đổi (ví dụ thêm "Khai vị", "Combo"), bạn sẽ phải sửa ở tất cả các hàm.

Khi số lượng trang tăng lên (20 trang hoặc hơn), việc sửa thủ công dễ gây thiếu sót, lỗi không đồng bộ.

Code trở nên khó đọc, khó mở rộng, và dễ tạo ra bug khi một số nơi được cập nhật còn một số nơi thì không.
