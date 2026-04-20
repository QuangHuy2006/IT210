Câu 1:
Trong đoạn code gốc, bạn Fresher đã dùng HttpServletRequest để lưu giỏ hàng. Vấn đề là:

Request chỉ sống trong một vòng đời duy nhất. Khi bạn gọi /add-to-cart, giỏ hàng được gắn vào request đó.

Sau khi redirect:/checkout, trình duyệt tạo một request mới hoàn toàn đến /checkout. Lúc này, dữ liệu gắn trong request cũ không còn tồn tại, nên giỏ hàng bị reset về null.

Vì vậy, lỗi không phải do Spring Boot hay do lệnh redirect sai, mà do sử dụng sai scope lưu trữ. Request scope không phù hợp cho dữ liệu cần tồn tại qua nhiều request.

Giải pháp: dùng HttpSession (hoặc một cơ chế lưu trữ lâu hơn như database, Redis, v.v.) để đảm bảo giỏ hàng tồn tại xuyên suốt nhiều request.

Câu 2:
```java
@Controller
public class CartController {
    
    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam("productId") String productId, HttpSession session) {
        List<String> cart = (List<String>) session.getAttribute("myCart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        cart.add(productId);
        
        session.setAttribute("myCart", cart);
        
        return "redirect:/checkout";
    }
    
    @GetMapping("/checkout")
    public String viewCheckout(HttpSession session, Model model) {
        List<String> cart = (List<String>) session.getAttribute("myCart");

        if (cart == null || cart.isEmpty()) {
            model.addAttribute("message", "Giỏ hàng của bạn đang trống!");
        } else {
            model.addAttribute("message", "Bạn có " + cart.size() + " sản phẩm.");
        }
        return "checkout-page";
    }
}

```