package org.example.ss2.Bai5;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EmployeeService {
    private List<Employee> employees = new ArrayList<>();

    public EmployeeService() {
        employees.add(new Employee("NV001", "Nguyễn Thị Lan", "Kế toán", 15000000, new Date(), "Đang làm"));
        employees.add(new Employee("NV002", "Trần Văn Hùng", "Kỹ thuật", 25000000, new Date(), "Đang làm"));
        employees.add(new Employee("NV003", "Lê Minh Đức", "Kinh doanh", 18500000, new Date(), "Nghỉ phép"));
        employees.add(new Employee("NV004", "Phạm Thu Hương", "Kỹ thuật", 22000000, new Date(), "Đang làm"));
        employees.add(new Employee("NV005", "Hoàng Văn Nam", "Kế toán", 12000000, new Date(), "Thử việc"));
    }

    public List<Employee> getAll() { return employees; }

    public Employee getByCode(String code) {
        return employees.stream().filter(e -> e.getCode().equals(code)).findFirst().orElse(null);
    }

    public double calculateTechDeptTotalSalary() {
        return employees.stream()
                .filter(e -> "Kỹ thuật".equals(e.getDepartment()))
                .mapToDouble(Employee::getSalary)
                .sum();
    }
}