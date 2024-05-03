package com.example.satellitelaunchersproject.dto;

import com.example.satellitelaunchersproject.pojo.CustomerSatellite;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSatelliteResponse {

    @JsonProperty("customer_satellites")
    private CustomerSatellite[] launchers;
}
