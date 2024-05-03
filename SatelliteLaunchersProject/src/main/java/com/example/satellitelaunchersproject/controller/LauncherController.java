package com.example.satellitelaunchersproject.controller;

import com.example.satellitelaunchersproject.pojo.CustomerSatellite;
import com.example.satellitelaunchersproject.pojo.SatelliteLauncher;
import com.example.satellitelaunchersproject.service.CustomerSatelliteService;
import com.example.satellitelaunchersproject.service.LauncherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/launcher")
public class LauncherController {

    @Autowired
    private  LauncherService launcherService;


    @GetMapping("/launcher-data")
    public ResponseEntity<String> fetchAndDumpLaunchersData() {
        try {
            List<SatelliteLauncher> launchers = launcherService.fetchAndParseLaunchersData();
            launcherService.launcherRepository.saveAll(launchers);
            return ResponseEntity.ok("Data fetched, parsed, and dumped successfully.");
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
            return ResponseEntity.status(500).body("Error occurred while fetching and dumping data.");
        }
    }

    @GetMapping("get")
    public List<SatelliteLauncher> getAll() {
        return launcherService.getAll();
    }

    @GetMapping("get/{id}")
    public ResponseEntity<SatelliteLauncher> getById(@PathVariable String id) {
        SatelliteLauncher launcher = launcherService.getById(id);
        if (launcher == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(launcher);
    }

    @PostMapping("insert")
    public ResponseEntity<SatelliteLauncher> create(@RequestBody SatelliteLauncher launcher) {
        SatelliteLauncher createdLauncher = launcherService.create(launcher);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLauncher);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<SatelliteLauncher> update(@PathVariable String id, @RequestBody SatelliteLauncher updatedLauncher) {
        SatelliteLauncher launcher = launcherService.update(id, updatedLauncher);
        if (launcher == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(launcher);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        boolean deleted = launcherService.delete(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }


}
