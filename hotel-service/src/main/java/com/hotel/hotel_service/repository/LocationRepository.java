package com.hotel.hotel_service.repository;

import com.hotel.hotel_service.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    @Query("SELECT l FROM Location l WHERE l.country = :country")
    List<Location> findByCountry(@Param("country") String country);

    @Query("SELECT l FROM Location l WHERE l.region = :region")
    List<Location> findByRegion(@Param("region") String region);
}