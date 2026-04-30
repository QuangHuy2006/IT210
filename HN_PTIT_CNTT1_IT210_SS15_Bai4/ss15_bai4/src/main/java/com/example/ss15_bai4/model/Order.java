package com.example.ss15_bai4.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(name = "order_detailId")
    private Long order_detailId;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "totalAmount")
    private Double totalAmount;

    @Column(name = "status")
    private String status;

    @Column(name = "orderDate")
    private LocalDate orderDate;
}