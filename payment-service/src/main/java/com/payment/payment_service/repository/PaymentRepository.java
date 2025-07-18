package com.payment.payment_service.repository;

import com.payment.payment_service.entity.Payment;
import com.payment.payment_service.entity.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    
    List<Payment> findByUserId(Integer userId);
    
    List<Payment> findByReservationId(Integer reservationId);
    
    List<Payment> findByStatus(PaymentStatus status);
    
    List<Payment> findByPaid(Boolean paid);
    
    @Query("SELECT p FROM Payment p WHERE p.roomReservationId = :roomReservationId")
    List<Payment> findByRoomReservationId(@Param("roomReservationId") Integer roomReservationId);
    
    @Query("SELECT p FROM Payment p WHERE p.transportReservationId = :transportReservationId")
    List<Payment> findByTransportReservationId(@Param("transportReservationId") Integer transportReservationId);
}
