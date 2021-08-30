package com.wego.parking.clients;

import com.wego.parking.model.CarparkAvailability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClient {

    @Autowired
    RestTemplate restTemplate;

    public CarparkAvailability getCarparkAvailabilities() {
        return restTemplate.getForEntity("https://api.data.gov.sg/v1/transport/carpark-availability", CarparkAvailability.class).getBody();
    }
}
