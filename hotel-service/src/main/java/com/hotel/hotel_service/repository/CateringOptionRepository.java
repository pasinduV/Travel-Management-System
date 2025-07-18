package com.hotel.hotel_service.repository;

import com.hotel.hotel_service.entity.CateringOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CateringOptionRepository extends JpaRepository<CateringOption, Long> {

    @Query("SELECT c FROM CateringOption c WHERE c.hotel.idHotel = :hotelId")
    List<CateringOption> findByHotelId(@Param("hotelId") Long hotelId);
}