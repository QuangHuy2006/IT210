package org.example.ss13_b3.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "prescriptions")
public class Prescription {
//   Trong JPA/Hibernate, @Id dùng để đánh dấu trường khóa chính (Primary Key) của một entity.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "patient_name", nullable = false)
    private String patientName;
//    Đây là annotation để định nghĩa quan hệ một-nhiều (One-to-Many) giữa hai entity.
//    Khi dùng @OneToMany, bạn mô tả rằng một bản ghi cha có thể liên kết với nhiều bản ghi con.
//    Cần kết hợp với mappedBy để chỉ rõ trường bên entity con dùng để tham chiếu ngược lại.
    @OneToMany(mappedBy = "prescription",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<PrescriptionDetail> details;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public List<PrescriptionDetail> getDetails() { return details; }
    public void setDetails(List<PrescriptionDetail> details) { this.details = details; }
}
