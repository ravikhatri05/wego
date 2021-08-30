package com.wego.parking.repository;

import com.wego.parking.entities.CarParking;
import com.wego.parking.model.projection.NearestParkingView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CarParkingRepository extends CrudRepository<CarParking, String> {

    List<CarParking> findAll();

    @Modifying
    @Transactional
    @Query(value = "update car_parking set is_parking_available= false", nativeQuery = true)
    void updateAvailabilityStatusFalse();


    @Modifying
    @Transactional
    @Query(value = "update car_parking set is_parking_available= ?4, total_lots=?2, lots_available=?3  where car_park_no = ?1 ", nativeQuery = true)
    void updateAvailability(String id, int totalLots, int availableLots, boolean isParkingAvailable);

    @Query(value = "SELECT car_park_no as id, address, x_coord as latitude, y_coord as longitude,total_lots as totalLots, lots_available as lotsAvailable, " +
            "(" +
            "   3959 *" +
            "   acos(cos(radians(?1)) *" +
            "   cos(radians(x_coord)) * " +
            "   cos(radians(y_coord) - " +
            "   radians(?2)) + " +
            "   sin(radians(?1)) * " +
            "   sin(radians(x_coord)))" +
            ") AS distance " +
            "FROM car_parking where is_parking_available = true ORDER BY distance",
            countQuery = "SELECT count(*) FROM car_parking where is_parking_available = true",
            nativeQuery = true)
    Page<NearestParkingView> getNearestParkingByLatAndLongPaginated(double lat, double lng, Pageable pageable);
}
