package com.payment.payment_service.dto;

import com.payment.payment_service.entity.PaymentMethod;
import com.payment.payment_service.entity.PaymentStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public class PaymentCreateRequest {
    @NotNull(message = "Reservation ID is required")
    private Integer reservationId;

    @NotNull(message = "User ID is required")
    private Integer userId;

    private Integer roomReservationId;

    private Integer transportReservationId;

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private BigDecimal amount;

    @NotNull(message = "Payment method is required")
    private PaymentMethod method;

    // Constructors
    public PaymentCreateRequest() {
    }

    public PaymentCreateRequest(Integer reservationId, Integer userId, Integer roomReservationId, 
                               Integer transportReservationId, BigDecimal amount, PaymentMethod method) {
        this.reservationId = reservationId;
        this.userId = userId;
        this.roomReservationId = roomReservationId;
        this.transportReservationId = transportReservationId;
        this.amount = amount;
        this.method = method;
    }

    // Getters and Setters
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

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }
}
