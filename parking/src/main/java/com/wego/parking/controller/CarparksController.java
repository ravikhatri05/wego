package com.wego.parking.controller;

import com.wego.parking.clients.RestClient;
import com.wego.parking.entities.CarParking;
import com.wego.parking.model.CarparkAvailability;
import com.wego.parking.service.CarparkingService;
import com.wego.parking.util.csv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("carparks")
@Validated
public class CarparksController {

    @Autowired
    RestClient restClient;

    @Autowired
    CarparkingService carparkingService;

    @Autowired
    ResourceLoader resourceLoader;

    @GetMapping("/config/sync")
    public void getCartParking() {
        carparkingService.syncCarParkingStatus();
    }

    @GetMapping("/config/test")
    public CarparkAvailability getList() {
        return restClient.getCarparkAvailabilities();
    }

    @GetMapping("/config/upload")
    public List<CarParking> upload() throws Exception {
        List<CarParking> carParkings = new CSVReader<CarParking>(resourceLoader).readCsv("parkingCSV/hdb-carpark-information.csv", new CarParking());
        carparkingService.saveAll(carParkings);
        return carparkingService.findAllParkings();
    }

    @GetMapping("/")
    public List<CarParking> findAll() {
        return carparkingService.findAllParkings();
    }

    @GetMapping("/nearest")
    public Object findNearest(@RequestParam(value = "latitude") @NotNull double lat,
                              @RequestParam(value = "longitude") @NotNull double lng,
                              @RequestParam(defaultValue = "0") @Min(0) int page, @RequestParam(name = "par_page", defaultValue = "10") @Min(3) int perPage) {
        return carparkingService.findNearest(lat, lng, page, perPage);
    }

}
