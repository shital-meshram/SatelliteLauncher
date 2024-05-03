package com.example.satellitelaunchersproject.dto;

import com.example.satellitelaunchersproject.pojo.SatelliteLauncher;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LauncherApiResponse {

    @JsonProperty("launchers")
    private SatelliteLauncher[] launchers;
}
