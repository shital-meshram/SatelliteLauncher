package com.example.satellitelaunchersproject.pojo;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "satellite_launchers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SatelliteLauncher {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Enumerated(EnumType.STRING)
    private LauncherType type;

    @Temporal(TemporalType.DATE)
    private Date registeredOn;

}

