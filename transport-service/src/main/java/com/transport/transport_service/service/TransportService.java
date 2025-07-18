package com.transport.transport_service.service;

import com.transport.transport_service.dto.*;
import com.transport.transport_service.entity.*;
import com.transport.transport_service.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TransportService {

    @Autowired
    private TransportRepository transportRepository;

    @Autowired
    private TransportCourseRepository transportCourseRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private TransportReservationRepository transportReservationRepository;

    // 1. Create Transport
    public TransportResponse createTransport(TransportCreateRequest request) {
        TransportCourse course;
        
        if (request.getCourseId() != null) {
            // Use existing course
            course = transportCourseRepository.findById(request.getCourseId())
                    .orElseThrow(() -> new RuntimeException("Transport course not found with ID: " + request.getCourseId()));
        } else if (request.getDepartureFromId() != null && request.getArrivalAtId() != null && request.getType() != null) {
            // Create course inline
            TransportCourseResponse courseResponse = createTransportCourse(
                    request.getDepartureFromId(), 
                    request.getArrivalAtId(), 
                    request.getType()
            );
            course = transportCourseRepository.findById(courseResponse.getId())
                    .orElseThrow(() -> new RuntimeException("Failed to create transport course"));
        } else {
            throw new RuntimeException("Either courseId OR (departureFromId + arrivalAtId + type) must be provided");
        }

        Transport transport = new Transport();
        transport.setCourse(course);
        transport.setCapacity(request.getCapacity());
        transport.setDepartureDate(request.getDepartureDate());
        transport.setPricePerAdult(request.getPricePerAdult());

        Transport savedTransport = transportRepository.save(transport);
        return convertToTransportResponse(savedTransport);
    }

    // 2. Get All Transports
    public List<TransportResponse> getAllTransports() {
        List<Transport> transports = transportRepository.findAll();
        return transports.stream()
                .map(this::convertToTransportResponse)
                .collect(Collectors.toList());
    }

    // 3. Get Transport by ID
    public TransportResponse getTransportById(Long id) {
        Transport transport = transportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transport not found with ID: " + id));
        return convertToTransportResponse(transport);
    }

    // 4. Delete Transport
    public void deleteTransport(Long id) {
        if (!transportRepository.existsById(id)) {
            throw new RuntimeException("Transport not found with ID: " + id);
        }
        transportRepository.deleteById(id);
    }

    // Additional methods for course management
    public TransportCourseResponse createTransportCourse(Long departureFromId, Long arrivalAtId, TransportType type) {
        Location departureFrom = locationRepository.findById(departureFromId)
                .orElseThrow(() -> new RuntimeException("Departure location not found with ID: " + departureFromId));
        
        Location arrivalAt = locationRepository.findById(arrivalAtId)
                .orElseThrow(() -> new RuntimeException("Arrival location not found with ID: " + arrivalAtId));

        TransportCourse course = new TransportCourse(departureFrom, arrivalAt, type);
        TransportCourse savedCourse = transportCourseRepository.save(course);
        return convertToTransportCourseResponse(savedCourse);
    }

    public List<TransportCourseResponse> getAllTransportCourses() {
        List<TransportCourse> courses = transportCourseRepository.findAll();
        return courses.stream()
                .map(this::convertToTransportCourseResponse)
                .collect(Collectors.toList());
    }

    public List<TransportResponse> getTransportsByCourseId(Long courseId) {
        List<Transport> transports = transportRepository.findByCourseId(courseId);
        return transports.stream()
                .map(this::convertToTransportResponse)
                .collect(Collectors.toList());
    }

    // Reservation methods
    public TransportReservationResponse createReservation(TransportReservationCreateRequest request) {
        Transport transport = transportRepository.findById(request.getTransportId())
                .orElseThrow(() -> new RuntimeException("Transport not found with ID: " + request.getTransportId()));

        // Check if there are enough seats available
        Integer totalReservedSeats = transportReservationRepository.getTotalReservedSeatsByTransportId(request.getTransportId());
        if (totalReservedSeats == null) {
            totalReservedSeats = 0;
        }
        
        int availableSeats = transport.getCapacity() - totalReservedSeats;
        if (request.getNumberOfSeats() > availableSeats) {
            throw new RuntimeException("Not enough seats available. Available: " + availableSeats + ", Requested: " + request.getNumberOfSeats());
        }

        TransportReservation reservation = new TransportReservation(transport, request.getNumberOfSeats());
        TransportReservation savedReservation = transportReservationRepository.save(reservation);
        return convertToTransportReservationResponse(savedReservation);
    }

    public List<TransportReservationResponse> getAllReservations() {
        List<TransportReservation> reservations = transportReservationRepository.findAll();
        return reservations.stream()
                .map(this::convertToTransportReservationResponse)
                .collect(Collectors.toList());
    }

    // Conversion methods
    private TransportResponse convertToTransportResponse(Transport transport) {
        TransportCourseResponse courseResponse = convertToTransportCourseResponse(transport.getCourse());
        return new TransportResponse(
                transport.getIdTransport(),
                courseResponse,
                transport.getCapacity(),
                transport.getDepartureDate(),
                transport.getPricePerAdult()
        );
    }

    private TransportCourseResponse convertToTransportCourseResponse(TransportCourse course) {
        LocationResponse departureFromResponse = convertToLocationResponse(course.getDepartureFrom());
        LocationResponse arrivalAtResponse = convertToLocationResponse(course.getArrivalAt());
        return new TransportCourseResponse(
                course.getId(),
                departureFromResponse,
                arrivalAtResponse,
                course.getType()
        );
    }

    private LocationResponse convertToLocationResponse(Location location) {
        return new LocationResponse(
                location.getId(),
                location.getCountry(),
                location.getRegion()
        );
    }

    private TransportReservationResponse convertToTransportReservationResponse(TransportReservation reservation) {
        TransportResponse transportResponse = convertToTransportResponse(reservation.getTransport());
        return new TransportReservationResponse(
                reservation.getIdTransportReservation(),
                transportResponse,
                reservation.getNumberOfSeats()
        );
    }
}
