

Phần 1

Quy tắc
- Mã tour (VN_12345/INT_12345) -> @Pattern
- adultPrice > 0 &&  childPrice > 0 | @Min(1)
- childPrice ≤ adultPrice -> @ValidPrice
- startDate ≥ today
- endDate > startDate

Luồng dữ liệu (Flow)

User nhập dữ liệu trên form (Thymeleaf)
↓
Click Submit
↓
Request gửi tới Controller (@PostMapping)
↓
Spring bind dữ liệu → TourDTO
↓
@Valid kích hoạt Hibernate Validator
↓
Field-level validation
Class-level validation
  ↓
  Nếu lỗi → BindingResult chứa error
  ↓
  Controller return lại "create-tour"
  ↓
  Thymeleaf hiển thị lỗi + giữ dữ liệu cũ
  ↓
  Không crash server




Case 1 – Sai format mã (tourCode = VN_12A45) -
- Bị chặn bởi @Pattern
- Không gây crash


Case 2 – Giá trẻ em lớn hơn
adult = 100
child = 200
Bị chặn bởi @ValidPrice
Case 3 – Ngày sai
startDate = 2026-05-10
endDate = 2026-05-01
Bị chặn bởi @ValidTourDate
