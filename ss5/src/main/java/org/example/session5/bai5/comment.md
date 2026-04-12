Hệ thống sẽ tuân thủ nghiêm ngặt nguyên lý Separation of Concerns (SoC) 
để đảm bảo View là "Dumb View".

OrderController: Chỉ làm nhiệm vụ điều hướng, nhận yêu cầu từ người dùng và trả về View tương ứng.

OrderService: "Trái tim" của hệ thống. Chứa toàn bộ Business Logic (tính thuế 10%, phí dịch vụ, tổng tiền).

OrderRepository: Đóng vai trò tầng dữ liệu (Mock in-memory). Cô lập logic truy vấn để Service
không cần quan tâm dữ liệu lấy từ đâu.