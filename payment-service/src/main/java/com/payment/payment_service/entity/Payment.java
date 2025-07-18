package com.payment.payment_service.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reservation_id", nullable = false)
    private Integer reservationId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "room_reservation_id")
    private Integer roomReservationId;

    @Column(name = "transport_reservation_id")
    private Integer transportReservationId;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private PaymentStatus status = PaymentStatus.PENDING;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    @Column(nullable = false)
    private Boolean paid = false;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (status == null) {
            status = PaymentStatus.PENDING;
        }
        if (paid == null) {
            paid = false;
        }
    }

    // Constructors
    public Payment() {
    }

    public Payment(Integer reservationId, Integer userId, Integer roomReservationId, 
                   Integer transportReservationId, BigDecimal amount, PaymentMethod method) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.roomReservationId = roomReservationId;
        this.transportReservationId = transportReservationId;
        this.amount = amount;
        this.method = method;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoomReservationId() {
        return roomReservationId;
    }

    public void setRoomReservationId(Integer roomReservationId) {
        this.roomReservationId = roomReservationId;
    }

    public Integer getTransportReservationId() {
        return transportReservationId;
    }

    public void setTransportReservationId(Integer transportReservationId) {
        this.transportReservationId = transportReservationId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
