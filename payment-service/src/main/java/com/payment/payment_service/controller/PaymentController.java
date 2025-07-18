package com.payment.payment_service.controller;

import com.payment.payment_service.dto.*;
import com.payment.payment_service.entity.PaymentStatus;
import com.payment.payment_service.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // API 1: Create Payment
    @PostMapping
    public ResponseEntity<ApiResponse<PaymentResponse>> createPayment(@Valid @RequestBody PaymentCreateRequest request) {
        try {
            PaymentResponse response = paymentService.createPayment(request);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(ApiResponse.success("Payment created successfully", response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    // API 2: Get All Payments
    @GetMapping
    public ResponseEntity<ApiResponse<List<PaymentResponse>>> getAllPayments() {
        try {
            List<PaymentResponse> payments = paymentService.getAllPayments();
            return ResponseEntity.ok(ApiResponse.success("Payments retrieved successfully", payments));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    // API 3: Get Payment by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PaymentResponse>> getPaymentById(@PathVariable Long id) {
        try {
            PaymentResponse response = paymentService.getPaymentById(id);
            return ResponseEntity.ok(ApiResponse.success("Payment retrieved successfully", response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    // API 4: Update Payment Status
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PaymentResponse>> updatePayment(@PathVariable Long id,
            @Valid @RequestBody PaymentUpdateRequest request) {
        try {
            PaymentResponse response = paymentService.updatePayment(id, request);
            return ResponseEntity.ok(ApiResponse.success("Payment updated successfully", response));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    // Additional APIs for filtering
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse<List<PaymentResponse>>> getPaymentsByUserId(@PathVariable Integer userId) {
        try {
            List<PaymentResponse> payments = paymentService.getPaymentsByUserId(userId);
            return ResponseEntity.ok(ApiResponse.success("Payments for user retrieved successfully", payments));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/reservation/{reservationId}")
    public ResponseEntity<ApiResponse<List<PaymentResponse>>> getPaymentsByReservationId(@PathVariable Integer reservationId) {
        try {
            List<PaymentResponse> payments = paymentService.getPaymentsByReservationId(reservationId);
            return ResponseEntity.ok(ApiResponse.success("Payments for reservation retrieved successfully", payments));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse<List<PaymentResponse>>> getPaymentsByStatus(@PathVariable PaymentStatus status) {
        try {
            List<PaymentResponse> payments = paymentService.getPaymentsByStatus(status);
            return ResponseEntity.ok(ApiResponse.success("Payments with status retrieved successfully", payments));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }

    // Bonus API: Delete Payment
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePayment(@PathVariable Long id) {
        try {
            paymentService.deletePayment(id);
            return ResponseEntity.ok(ApiResponse.success("Payment deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.error(e.getMessage()));
        }
    }
}
