Vấn đề:
- Dùng PriceLessThan → chỉ lấy giá < maxPrice --> sai nghiệp vụ (thiếu trường hợp bằng)
- Không có điều kiện stockQuantity > 0 --> trả về cả sản phẩm hết hàng

Dẫn đến:
- Thiếu sản phẩm giá đúng bằng maxPrice
- Hiển thị sản phẩm đã hết hàng --> gây trải nghiệm xấu
