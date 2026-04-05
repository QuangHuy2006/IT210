package ss1.b2;

public class PlaySession {
    private double playTime = 0;

    public void addTime(double time) {
        this.playTime += time;
    }

    /*
     * GIẢI THÍCH TẠI SAO TÍNH NHẦM TIỀN (Lỗi Singleton Scope):
     * 1. Cơ chế Singleton mặc định: Trong Spring, một Bean được đánh dấu @Component
     * mặc định sẽ là Singleton. Nghĩa là Spring Container chỉ tạo ra DUY NHẤT một
     * thực thể (instance) của PlaySession cho toàn bộ ứng dụng.
     * * 2. Hiện tượng dùng chung dữ liệu: Vì chỉ có một đối tượng duy nhất, nên mọi
     * máy trạm (khách hàng) trong phòng máy đều sẽ truy cập và thay đổi trên cùng
     * một biến 'playTime' này.
     * * 3. Kết quả sai lệch: Khi khách hàng A thêm giờ, giá trị 'playTime' tăng lên.
     * Ngay sau đó khách hàng B thêm giờ, giá trị này lại tiếp tục cộng dồn vào con
     * số của khách A thay vì tính riêng từ đầu. Điều này dẫn đến việc cộng dồn thời
     * gian của tất cả người chơi vào một người duy nhất, gây ra tính sai tiền nghiêm trọng.
     * * 4. Cách khắc phục: Phải đổi Scope của Bean này sang "prototype" bằng annotation
     * @Scope("prototype") để mỗi khách hàng khi bắt đầu ván game sẽ được cấp một
     * thực thể PlaySession riêng biệt.
     */
}
