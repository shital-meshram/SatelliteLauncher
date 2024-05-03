package com.example.satellitelaunchersproject.repository;

import com.example.satellitelaunchersproject.pojo.CustomerSatellite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerSatellite, String> {

    @Query("SELECT s FROM CustomerSatellite s " +
            "WHERE (:id IS NULL OR s.id = :id) " +
            "AND (:launchDate IS NULL OR s.launchDate = :launchDate) " +
            "AND (:country IS NULL OR s.country = :country) " +
            "AND (:launcher IS NULL OR s.launcher = :launcher) " +
            "AND (:mass IS NULL OR s.mass = :mass)")
    List<CustomerSatellite> search(
            @Param("id") String id,
            @Param("launchDate") String launchDate,
            @Param("country") String country,
            @Param("launcher") String launcher,
            @Param("mass") Integer mass
    );
}
