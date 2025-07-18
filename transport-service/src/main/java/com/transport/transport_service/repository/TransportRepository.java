package com.transport.transport_service.repository;

import com.transport.transport_service.entity.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransportRepository extends JpaRepository<Transport, Long> {

    @Query("SELECT t FROM Transport t WHERE t.course.id = :courseId")
    List<Transport> findByCourseId(@Param("courseId") Long courseId);

    @Query("SELECT t FROM Transport t WHERE t.departureDate = :departureDate")
    List<Transport> findByDepartureDate(@Param("departureDate") LocalDate departureDate);

    @Query("SELECT t FROM Transport t WHERE t.departureDate BETWEEN :startDate AND :endDate")
    List<Transport> findByDepartureDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT t FROM Transport t WHERE t.pricePerAdult <= :maxPrice")
    List<Transport> findByPricePerAdultLessThanEqual(@Param("maxPrice") Float maxPrice);

    @Query("SELECT t FROM Transport t WHERE t.capacity >= :minCapacity")
    List<Transport> findByCapacityGreaterThanEqual(@Param("minCapacity") Integer minCapacity);

    @Query("SELECT t FROM Transport t WHERE t.course.id = :courseId AND t.departureDate = :departureDate")
    List<Transport> findByCourseIdAndDepartureDate(@Param("courseId") Long courseId, @Param("departureDate") LocalDate departureDate);
}
