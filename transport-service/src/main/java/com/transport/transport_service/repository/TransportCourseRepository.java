package com.transport.transport_service.repository;

import com.transport.transport_service.entity.TransportCourse;
import com.transport.transport_service.entity.TransportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransportCourseRepository extends JpaRepository<TransportCourse, Long> {

    @Query("SELECT tc FROM TransportCourse tc WHERE tc.type = :type")
    List<TransportCourse> findByType(@Param("type") TransportType type);

    @Query("SELECT tc FROM TransportCourse tc WHERE tc.departureFrom.id = :departureFromId")
    List<TransportCourse> findByDepartureFromId(@Param("departureFromId") Long departureFromId);

    @Query("SELECT tc FROM TransportCourse tc WHERE tc.arrivalAt.id = :arrivalAtId")
    List<TransportCourse> findByArrivalAtId(@Param("arrivalAtId") Long arrivalAtId);

    @Query("SELECT tc FROM TransportCourse tc WHERE tc.departureFrom.id = :departureFromId AND tc.arrivalAt.id = :arrivalAtId")
    List<TransportCourse> findByDepartureFromIdAndArrivalAtId(@Param("departureFromId") Long departureFromId, @Param("arrivalAtId") Long arrivalAtId);

    @Query("SELECT tc FROM TransportCourse tc WHERE tc.departureFrom.id = :departureFromId AND tc.arrivalAt.id = :arrivalAtId AND tc.type = :type")
    List<TransportCourse> findByDepartureFromIdAndArrivalAtIdAndType(@Param("departureFromId") Long departureFromId, @Param("arrivalAtId") Long arrivalAtId, @Param("type") TransportType type);
}
