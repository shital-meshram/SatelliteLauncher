package com.example.satellitelaunchersproject.repository;

import com.example.satellitelaunchersproject.pojo.SatelliteLauncher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LauncherRepository extends JpaRepository<SatelliteLauncher, String> {
}
