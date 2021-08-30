package com.wego.parking.entities;

import com.wego.parking.model.CSVFormate;
import com.wego.parking.util.latlongConversion.LatLonCoordinate;
import com.wego.parking.util.latlongConversion.SVY21;
import com.wego.parking.util.latlongConversion.SVY21Coordinate;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "car_parking", indexes = {
        @Index(columnList = "xCoord,yCoord,isParkingAvailable")})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarParking extends CSVFormate implements Serializable {
    @Id
    private String carParkNo;
    private String address;
    private double xCoord;
    private double yCoord;
    private String carParkType;
    private String typeOfParkingSystem;
    private String shortTermParking;
    private String freeParking;
    private boolean nightParking;
    private String carParkDecks;
    private String gantryHeight;
    private boolean carParkBasement;
    private boolean isParkingAvailable;
    private int totalLots;
    private int lotsAvailable;

    @Override
    public CSVFormate constructFromStrings(List<String> values) {
        LatLonCoordinate latLonCoordinate = SVY21.computeLatLon(new SVY21Coordinate(Double.parseDouble(values.get(3)), Double.parseDouble(values.get(2))));
        return new CarParkingBuilder().carParkNo(values.get(0))
                .address(values.get(1))
                .xCoord(latLonCoordinate.getLatitude())
                .yCoord(latLonCoordinate.getLongitude())
                .carParkType(values.get(4))
                .typeOfParkingSystem(values.get(5))
                .shortTermParking(values.get(6))
                .freeParking(values.get(7))
                .nightParking(values.get(8).equalsIgnoreCase("YES"))
                .carParkDecks(values.get(9))
                .gantryHeight(values.get(10))
                .carParkBasement(values.get(8).equalsIgnoreCase("Y"))
                .totalLots(0)
                .lotsAvailable(0)
                .isParkingAvailable(false).build();
    }
}
