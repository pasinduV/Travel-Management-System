package com.hotel.hotel_service.repository;

import com.hotel.hotel_service.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    // Find hotels by location
    @Query("SELECT h FROM Hotel h WHERE h.location.locationId = :locationId")
    List<Hotel> findByLocationId(@Param("locationId") Long locationId);

    // Find hotels by rating greater than or equal
    @Query("SELECT h FROM Hotel h WHERE h.rating >= :minRating")
    List<Hotel> findByRatingGreaterThanEqual(@Param("minRating") BigDecimal minRating);

    // Find hotel with location details
    @Query("SELECT h FROM Hotel h LEFT JOIN FETCH h.location WHERE h.idHotel = :id")
    Optional<Hotel> findByIdWithLocation(@Param("id") Long id);

    // Find hotels with rooms and catering options
    @Query("SELECT h FROM Hotel h LEFT JOIN FETCH h.rooms LEFT JOIN FETCH h.cateringOptions WHERE h.idHotel = :id")
    Optional<Hotel> findByIdWithDetails(@Param("id") Long id);
}