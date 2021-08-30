package com.wego.parking.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CarparkAvailability implements Serializable {
    private List<Item> items;

    @Data
    public static class Item implements Serializable {
        private String timestamp;
        @JsonProperty("carpark_data")
        private List<CarparkData> carparkData;

        @Data
        public static class CarparkData implements Serializable {
            @JsonProperty("carpark_info")
            private List<CarparkInfo> carparkInfo;
            @JsonProperty("carpark_number")
            private String carparkNumber;
            @JsonProperty("update_datetime")
            private String updateDatetime;

            @Data
            public static class CarparkInfo implements Serializable {
                @JsonProperty("total_lots")
                private int totalLots;
                @JsonProperty("lot_type")
                private String lotType;
                @JsonProperty("lots_available")
                private int lotsAvailable;
            }
        }
    }
}
