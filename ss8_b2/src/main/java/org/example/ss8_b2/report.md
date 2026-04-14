# EmployeeController.java

```java
@Controller
public class EmployeeController {

    @PostMapping("/hr/add-employee")
    public String saveEmployee(
        @Valid @ModelAttribute("employee") EmployeeDto employee,
        Model model,
        BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "employee-form"; // Trả lại trang form nếu có lỗi
        }

        // Gọi Service lưu vào DB...
        return "redirect:/hr/success";
    }
}
```
Trong Spring MVC, khi dùng @Valid với BindingResult, BindingResult phải đứng ngay sau đối tượng được validate. Nếu bạn đặt Model model chen vào giữa, Spring sẽ không coi bindingResult là kết quả validate của employee, dẫn đến exception và trả về 400.
Solution:

```java
@Controller
public class EmployeeController {

    @PostMapping("/hr/add-employee")
    public String saveEmployee(
        @Valid @ModelAttribute("employee") EmployeeDto employee,
        BindingResult bindingResult,
        Model model) {

        if (bindingResult.hasErrors()) {
            return "employee-form"; // Trả lại form kèm lỗi
        }

        // Gọi Service lưu vào DB...
        return "redirect:/hr/success";
    }
}
```

```html
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <div class="form-group">
        <label for="age"></label>
        <input type="number"
               class="form-control" name="" id="age" placeholder="0" th:field="*{age}">
        <small th:if="${#field.hasErrors('age')}" th:errors="*{age}" style="color: red"></small>
    </div>
</body>
</html>
```
