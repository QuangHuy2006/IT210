package org.example.session02.Bai5;

import java.util.Date;

public class Employee {
    private String code;
    private String name;
    private String department;
    private int salary;
    private Date date;
    private String status;

    public Employee(String code, String name, String department, int salary, Date date, String status) {
        this.code = code;
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.date = date;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public int getSalary() {
        return salary;
    }

    public Date getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }
}