//package ss1.b1;
//
//public class RechargeService {
//    private PaymentGateway gateway;
//
//    public RechargeService() {
//        // Lỗi: Tự khởi tạo thủ công (Hard-code dependency)
//        // ĐOẠN CODE SAI:
//        this.gateway = new InternalPaymentGateway();
//
//        /* * GIẢI THÍCH TẠI SAO SAI (Dựa trên IoC):
//         * 1. Vi phạm nguyên lý Inversion of Control (IoC): Class RechargeService đang tự
//         * nắm quyền khởi tạo phụ thuộc (InternalPaymentGateway) thay vì để hệ thống bên ngoài bơm vào.
//         * 2. Mất tính linh hoạt (Tight Coupling): Code bị trói chặt vào một Implementation cụ thể.
//         * Nếu muốn đổi sang cổng thanh toán khác, ta buộc phải sửa code và biên dịch lại class này.
//         * 3. Khó mở rộng & bảo trì: Việc thay thế hoặc giả lập (Mock) gateway để test
//         * logic nạp tiền trở nên cực kỳ khó khăn vì phụ thuộc đã bị "đóng cứng" bên trong.
//         */
//    }
//
//    public void processRecharge(String username, double amount) {
//        // Bẫy dữ liệu: Ngăn chặn username null/rỗng hoặc số tiền âm gây lỗi hệ thống
//        if (username == null || username.isEmpty() || amount <= 0) {
//            System.err.println("[LOG] Dữ liệu đầu vào không hợp lệ. Hủy giao dịch.");
//            return;
//        }
//
//        gateway.pay(amount);
//        System.out.println("Nạp " + amount + " cho " + username);
//    }
//}
