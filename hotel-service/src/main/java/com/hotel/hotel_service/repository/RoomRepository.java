package com.hotel.hotel_service.repository;

import com.hotel.hotel_service.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("SELECT r FROM Room r WHERE r.hotel.idHotel = :hotelId")
    List<Room> findByHotelId(@Param("hotelId") Long hotelId);

    @Query("SELECT r FROM Room r WHERE r.hotel.idHotel = :hotelId AND r.isAvailable = true")
    List<Room> findAvailableRoomsByHotelId(@Param("hotelId") Long hotelId);
}