package com.payment.payment_service.dto;

import com.payment.payment_service.entity.PaymentStatus;
import jakarta.validation.constraints.NotNull;

public class PaymentUpdateRequest {
    @NotNull(message = "Payment status is required")
    private PaymentStatus status;

    private Boolean paid;

    // Constructors
    public PaymentUpdateRequest() {
    }

    public PaymentUpdateRequest(PaymentStatus status, Boolean paid) {
        this.status = status;
        this.paid = paid;
    }

    // Getters and Setters
    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }
}
