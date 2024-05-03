package com.example.satellitelaunchersproject.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "customer_satellites")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSatellite {

    @Id
    private String id;
    private String country;
    @Temporal(TemporalType.DATE)
    @JsonProperty("launch_date")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date launchDate;
    private float mass;
    private String launcher;
}
