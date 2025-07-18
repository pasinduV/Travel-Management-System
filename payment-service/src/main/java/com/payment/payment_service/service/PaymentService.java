package com.payment.payment_service.service;

import com.payment.payment_service.dto.PaymentCreateRequest;
import com.payment.payment_service.dto.PaymentResponse;
import com.payment.payment_service.dto.PaymentUpdateRequest;
import com.payment.payment_service.entity.Payment;
import com.payment.payment_service.entity.PaymentStatus;
import com.payment.payment_service.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    // 1. Create Payment
    public PaymentResponse createPayment(PaymentCreateRequest request) {
        Payment payment = new Payment();
        payment.setReservationId(request.getReservationId());
        payment.setUserId(request.getUserId());
        payment.setRoomReservationId(request.getRoomReservationId());
        payment.setTransportReservationId(request.getTransportReservationId());
        payment.setAmount(request.getAmount());
        payment.setMethod(request.getMethod());

        Payment savedPayment = paymentRepository.save(payment);
        return convertToPaymentResponse(savedPayment);
    }

    // 2. Get All Payments
    public List<PaymentResponse> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream()
                .map(this::convertToPaymentResponse)
                .collect(Collectors.toList());
    }

    // 3. Get Payment by ID
    public PaymentResponse getPaymentById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with ID: " + id));
        return convertToPaymentResponse(payment);
    }

    // 4. Update Payment
    public PaymentResponse updatePayment(Long id, PaymentUpdateRequest request) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with ID: " + id));

        if (request.getStatus() != null) {
            payment.setStatus(request.getStatus());
            // If status is SUCCESS, automatically set paid to true
            if (request.getStatus() == PaymentStatus.SUCCESS) {
                payment.setPaid(true);
            }
        }
        
        if (request.getPaid() != null) {
            payment.setPaid(request.getPaid());
        }

        Payment updatedPayment = paymentRepository.save(payment);
        return convertToPaymentResponse(updatedPayment);
    }

    // Additional utility methods
    public List<PaymentResponse> getPaymentsByUserId(Integer userId) {
        List<Payment> payments = paymentRepository.findByUserId(userId);
        return payments.stream()
                .map(this::convertToPaymentResponse)
                .collect(Collectors.toList());
    }

    public List<PaymentResponse> getPaymentsByReservationId(Integer reservationId) {
        List<Payment> payments = paymentRepository.findByReservationId(reservationId);
        return payments.stream()
                .map(this::convertToPaymentResponse)
                .collect(Collectors.toList());
    }

    public List<PaymentResponse> getPaymentsByStatus(PaymentStatus status) {
        List<Payment> payments = paymentRepository.findByStatus(status);
        return payments.stream()
                .map(this::convertToPaymentResponse)
                .collect(Collectors.toList());
    }

    public void deletePayment(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with ID: " + id));
        paymentRepository.delete(payment);
    }

    // Conversion method
    private PaymentResponse convertToPaymentResponse(Payment payment) {
        return new PaymentResponse(
                payment.getId(),
                payment.getReservationId(),
                payment.getUserId(),
                payment.getRoomReservationId(),
                payment.getTransportReservationId(),
                payment.getAmount(),
                payment.getStatus(),
                payment.getMethod(),
                payment.getPaid(),
                payment.getCreatedAt()
        );
    }
}
