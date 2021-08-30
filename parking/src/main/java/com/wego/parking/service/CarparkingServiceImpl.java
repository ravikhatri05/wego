package com.wego.parking.service;

import com.wego.parking.clients.RestClient;
import com.wego.parking.entities.CarParking;
import com.wego.parking.model.CarparkAvailability;
import com.wego.parking.model.NearestCarParkingView;
import com.wego.parking.model.projection.NearestParkingView;
import com.wego.parking.repository.CarParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarparkingServiceImpl implements CarparkingService {

    @Autowired
    RestClient restClient;

    @Autowired
    CarParkingRepository carParkingRepository;

    @Override
    @Transactional
    public void syncCarParkingStatus() {
        List<NearestCarParkingView> parkingIds = getLatestAvailableParkings();
        carParkingRepository.updateAvailabilityStatusFalse();
        parkingIds.forEach(parkingId ->
                carParkingRepository.updateAvailability(parkingId.getId(), parkingId.getTotalLots(), parkingId.getAvailableLots(),
                        parkingId.getAvailableLots() > 0));
    }

    @Override
    public void saveAll(List<CarParking> carParkings) {
        carParkingRepository.saveAll(carParkings);
    }

    @Override
    public List<CarParking> findAllParkings() {
        return carParkingRepository.findAll();
    }

    @Override
    public List<NearestParkingView> findNearest(double lat, double lon, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return carParkingRepository.getNearestParkingByLatAndLongPaginated(lat, lon, pageable).getContent();
    }

    private List<NearestCarParkingView> getLatestAvailableParkings() {
        return restClient.getCarparkAvailabilities().getItems().get(0).getCarparkData()
                .parallelStream()
                .map(carparkData -> {
                    int totalParking = 0;
                    int availableParking = 0;
                    for (CarparkAvailability.Item.CarparkData.CarparkInfo carparkInfo : carparkData.getCarparkInfo()) {
                        totalParking += carparkInfo.getTotalLots();
                        availableParking += carparkInfo.getLotsAvailable();
                    }
                    return new NearestCarParkingView(carparkData.getCarparkNumber(), null, 0d, 0d, totalParking, availableParking, 0d);
                }).collect(Collectors.toList());
    }
}
