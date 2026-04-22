package org.example.ss13_b3.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "prescriptiondetail")
public class PrescriptionDetail {
//   Trong JPA/Hibernate, @Id dùng để đánh dấu trường khóa chính (Primary Key) của một entity.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    Đây là annotation để định nghĩa quan hệ một-nhiều (One-to-Many) giữa hai entity.
//    Khi dùng @OneToMany, bạn mô tả rằng một bản ghi cha có thể liên kết với nhiều bản ghi con.
//    Cần kết hợp với mappedBy để chỉ rõ trường bên entity con dùng để tham chiếu ngược lại.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }
}
