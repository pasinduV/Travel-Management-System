package com.transport.transport_service.repository;

import com.transport.transport_service.entity.TransportReservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportReservationRepository extends JpaRepository<TransportReservation, Long> {

    @Query("SELECT tr FROM TransportReservation tr WHERE tr.transport.idTransport = :transportId")
    List<TransportReservation> findByTransportId(@Param("transportId") Long transportId);

    @Query("SELECT tr FROM TransportReservation tr WHERE tr.numberOfSeats >= :minSeats")
    List<TransportReservation> findByNumberOfSeatsGreaterThanEqual(@Param("minSeats") Integer minSeats);

    @Query("SELECT SUM(tr.numberOfSeats) FROM TransportReservation tr WHERE tr.transport.idTransport = :transportId")
    Integer getTotalReservedSeatsByTransportId(@Param("transportId") Long transportId);

    @Query("SELECT tr FROM TransportReservation tr WHERE tr.transport.course.id = :courseId")
    List<TransportReservation> findByCourseId(@Param("courseId") Long courseId);
}
