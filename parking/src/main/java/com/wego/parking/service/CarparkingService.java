package com.wego.parking.service;

import com.wego.parking.entities.CarParking;
import com.wego.parking.model.projection.NearestParkingView;

import java.util.List;

public interface CarparkingService {
    void syncCarParkingStatus();

    void saveAll(List<CarParking> carParkings);

    List<CarParking> findAllParkings();

    List<NearestParkingView> findNearest(double lat, double lon, int pageNo, int pageSize);
}
