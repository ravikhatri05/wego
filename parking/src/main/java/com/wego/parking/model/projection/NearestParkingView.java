package com.wego.parking.model.projection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public interface NearestParkingView {
    @JsonIgnore
    String getId();

    String getAddress();

    double getLatitude();

    double getLongitude();

    @JsonProperty("total_lots")
    int getTotalLots();

    @JsonProperty("available_lots")
    int getLotsAvailable();

    @JsonIgnore
    double getDistance();
}
