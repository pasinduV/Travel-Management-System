// filepath: hotel-service/src/main/java/com/hotel/hotel_service/service/HotelService.java
package com.hotel.hotel_service.service;

import com.hotel.hotel_service.dto.*;
import com.hotel.hotel_service.entity.*;
import com.hotel.hotel_service.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private CateringOptionRepository cateringOptionRepository;

    // 1. Create Hotel
    public HotelResponse createHotel(HotelCreateRequest request) {
        Location location = locationRepository.findById(request.getLocationId())
                .orElseThrow(() -> new RuntimeException("Location not found with ID: " + request.getLocationId()));

        Hotel hotel = new Hotel();
        hotel.setName(request.getName());
        hotel.setRating(request.getRating());
        hotel.setDescription(request.getDescription());
        hotel.setPhotos(request.getPhotos());
        hotel.setLocation(location);

        Hotel savedHotel = hotelRepository.save(hotel);
        return convertToHotelResponse(savedHotel);
    }

    // 2. Get All Hotels
    public List<HotelResponse> getAllHotels() {
        List<Hotel> hotels = hotelRepository.findAll();
        return hotels.stream()
                .map(this::convertToHotelResponse)
                .collect(Collectors.toList());
    }

    // 3. Get Hotel by ID with Details
    public HotelResponse getHotelById(Long id) {
        Hotel hotel = hotelRepository.findByIdWithLocation(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found with ID: " + id));

        return convertToHotelResponseWithDetails(hotel);
    }

    // 4. Update Hotel
    public HotelResponse updateHotel(Long id, HotelUpdateRequest request) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found with ID: " + id));

        if (request.getName() != null) {
            hotel.setName(request.getName());
        }
        if (request.getRating() != null) {
            hotel.setRating(request.getRating());
        }
        if (request.getDescription() != null) {
            hotel.setDescription(request.getDescription());
        }
        if (request.getPhotos() != null) {
            hotel.setPhotos(request.getPhotos());
        }
        if (request.getLocationId() != null) {
            Location location = locationRepository.findById(request.getLocationId())
                    .orElseThrow(() -> new RuntimeException("Location not found with ID: " + request.getLocationId()));
            hotel.setLocation(location);
        }

        Hotel updatedHotel = hotelRepository.save(hotel);
        return convertToHotelResponse(updatedHotel);
    }

    // Additional utility methods
    public List<HotelResponse> getHotelsByLocation(Long locationId) {
        List<Hotel> hotels = hotelRepository.findByLocationId(locationId);
        return hotels.stream()
                .map(this::convertToHotelResponse)
                .collect(Collectors.toList());
    }

    public void deleteHotel(Long id) {
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found with ID: " + id));
        hotelRepository.delete(hotel);
    }

    // Conversion methods
    private HotelResponse convertToHotelResponse(Hotel hotel) {
        HotelResponse response = new HotelResponse();
        response.setIdHotel(hotel.getIdHotel());
        response.setName(hotel.getName());
        response.setRating(hotel.getRating());
        response.setDescription(hotel.getDescription());
        response.setPhotos(hotel.getPhotos());
        response.setCreatedAt(hotel.getCreatedAt());
        response.setUpdatedAt(hotel.getUpdatedAt());

        if (hotel.getLocation() != null) {
            LocationResponse locationResponse = new LocationResponse();
            locationResponse.setLocationId(hotel.getLocation().getLocationId());
            locationResponse.setCountry(hotel.getLocation().getCountry());
            locationResponse.setRegion(hotel.getLocation().getRegion());
            locationResponse.setCreatedAt(hotel.getLocation().getCreatedAt());
            locationResponse.setUpdatedAt(hotel.getLocation().getUpdatedAt());
            response.setLocation(locationResponse);
        }

        return response;
    }

    private HotelResponse convertToHotelResponseWithDetails(Hotel hotel) {
        HotelResponse response = convertToHotelResponse(hotel);

        // Get rooms
        List<Room> rooms = roomRepository.findByHotelId(hotel.getIdHotel());
        List<RoomResponse> roomResponses = rooms.stream()
                .map(this::convertToRoomResponse)
                .collect(Collectors.toList());
        response.setRooms(roomResponses);

        // Get catering options
        List<CateringOption> cateringOptions = cateringOptionRepository.findByHotelId(hotel.getIdHotel());
        List<CateringOptionResponse> cateringResponses = cateringOptions.stream()
                .map(this::convertToCateringResponse)
                .collect(Collectors.toList());
        response.setCateringOptions(cateringResponses);

        return response;
    }

    private RoomResponse convertToRoomResponse(Room room) {
        return new RoomResponse(
                room.getIdRoom(),
                room.getName(),
                room.getGuestCapacity(),
                room.getPricePerAdult(),
                room.getDescription(),
                room.getIsAvailable(),
                room.getCreatedAt(),
                room.getUpdatedAt());
    }

    private CateringOptionResponse convertToCateringResponse(CateringOption catering) {
        return new CateringOptionResponse(
                catering.getIdCateringOption(),
                catering.getType(),
                catering.getPrice(),
                catering.getRating(),
                catering.getCreatedAt(),
                catering.getUpdatedAt());
    }
}