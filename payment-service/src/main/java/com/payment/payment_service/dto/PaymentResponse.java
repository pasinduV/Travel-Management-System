package com.payment.payment_service.dto;

import com.payment.payment_service.entity.PaymentMethod;
import com.payment.payment_service.entity.PaymentStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentResponse {
    private Long id;
    private Integer reservationId;
    private Integer userId;
    private Integer roomReservationId;
    private Integer transportReservationId;
    private BigDecimal amount;
    private PaymentStatus status;
    private PaymentMethod method;
    private Boolean paid;
    private LocalDateTime createdAt;

    // Constructors
    public PaymentResponse() {
    }

    public PaymentResponse(Long id, Integer reservationId, Integer userId, Integer roomReservationId,
                          Integer transportReservationId, BigDecimal amount, PaymentStatus status,
                          PaymentMethod method, Boolean paid, LocalDateTime createdAt) {
        this.id = id;
        this.reservationId = reservationId;
        this.userId = userId;
        this.roomReservationId = roomReservationId;
        this.transportReservationId = transportReservationId;
        this.amount = amount;
        this.status = status;
        this.method = method;
        this.paid = paid;
        this.createdAt = createdAt;
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
