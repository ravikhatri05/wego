package com.wego.parking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class NearestCarParkingView implements Serializable {
    @JsonIgnore
    private String id;
    private String address;
    private double latitude;
    private double longitude;
    @JsonProperty("total_lots")
    private int totalLots;
    @JsonProperty("available_lots")
    private int availableLots;
    @JsonIgnore
    private double distance;
}
