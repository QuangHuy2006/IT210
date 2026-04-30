Phân tích & Đề xuất
1. Input / Output
    - Input (từ Controller → Service):
      categoryName: String
      discountPercentage: Double
    - Output mong muốn:
      Số lượng sản phẩm đã được cập nhật (int)
    - Hoặc Exception nếu:
      % giảm giá không hợp lệ
      Không có sản phẩm nào thỏa điều kiện
2. So sánh hai giải pháp

Tiêu chí	            Giải pháp 1 (Load RAM)	            Giải pháp 2 (JPQL Update)
RAM	                     Rất tốn (50.000 entity)                	Rất ít
Performance	            Chậm (loop + nhiều SQL)             	Nhanh (1 câu SQL)
Số câu SQL            	Nhiều (SELECT + UPDATE từng dòng)       	1 UPDATE
Đồng bộ dữ liệu        	An toàn (Dirty Checking)            	Có thể lệch cache
Độ phức tạp	                    Dễ	                                Trung bình


==> Chọn Giải pháp 2 (JPQL UPDATE) vì:
Dataset lớn (50.000 record)
Tối ưu hiệu năng và tài nguyên
Phù hợp nghiệp vụ batch update