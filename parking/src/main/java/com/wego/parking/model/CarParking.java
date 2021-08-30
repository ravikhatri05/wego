package com.wego.parking.model;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarParking extends CSVFormate {
    private String carParkNo;
    private String address;
    private String xCoord;
    private String yCoord;
    private String carParkType;
    private String typeOfParkingSystem;
    private String shortTermParking;
    private String freeParking;
    private boolean nightParking;
    private String carParkDecks;
    private String gantryHeight;
    private boolean carParkBasement;

    @Override
    public CSVFormate constructFromStrings(List<String> values) {
        return new CarParkingBuilder().carParkNo(values.get(0))
                .address(values.get(1))
                .xCoord(values.get(2))
                .yCoord(values.get(3))
                .carParkType(values.get(4))
                .typeOfParkingSystem(values.get(5))
                .shortTermParking(values.get(6))
                .freeParking(values.get(7))
                .nightParking(values.get(8).equalsIgnoreCase("YES"))
                .carParkDecks(values.get(9))
                .gantryHeight(values.get(10))
                .carParkBasement(values.get(8).equalsIgnoreCase("Y")).build();
    }
}
