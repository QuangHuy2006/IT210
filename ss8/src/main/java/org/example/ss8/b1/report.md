# AddressDto.java

```java
public class AddressDto {
    @NotNull(message = "Tên người nhận không được thiếu")
    private String receiverName;

    @NotNull(message = "Địa chỉ không được thiếu")
    private String detailedAddress;

    // getters, setters...
}
```
# UserController.java
```java
@RestController
@RequestMapping("/api/address")
public class UserController {

    @PostMapping("/update")
    public ResponseEntity<String> updateAddress(@RequestBody AddressDto addressDto) {
        // Logic lưu database ở đây...
        return ResponseEntity.ok("Cập nhật địa chỉ thành công!");
    }
}
```

Câu 1:
Vì ở trong Java " " được coi là 1 chuỗi hợp lệ(length > 0) nên @NotNull sẽ không check được và để xử lý việc này thì sử dụng @NotBlank thay cho @NotNull vì @NotBlank sẽ check được khoảng trắng
Câu 2:
# AddressDto.java

```java
public class AddressDto {
    @NotBlank(message = "Tên người nhận không được thiếu")
    private String receiverName;

    @NotBlank(message = "Địa chỉ không được thiếu")
    private String detailedAddress;

    // getters, setters...
}
```

# UserController.java
```java
@RestController
@RequestMapping("/api/address")
public class UserController {

    @PostMapping("/update")
    public ResponseEntity<String> updateAddress(@RequestBody AddressDto addressDto) {
        // Logic lưu database ở đây...
        return ResponseEntity.ok("Cập nhật địa chỉ thành công!");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
```