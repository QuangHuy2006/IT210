package org.example.ss13_b5.model;

import jakarta.persistence.*;
import org.example.ss13_b5.model.PrescriptionDetail;

import java.util.List;

@Entity
@Table(name = "prescriptions")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "patient_name", nullable = false)
    private String patientName;

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
