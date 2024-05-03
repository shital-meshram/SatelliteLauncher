package com.example.satellitelaunchersproject.controller;

import com.example.satellitelaunchersproject.pojo.CustomerSatellite;
import com.example.satellitelaunchersproject.service.CustomerSatelliteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/customer-satellite")
public class CustomerSatelliteController {

    @Autowired
    private CustomerSatelliteService customerSatelliteService;

    @GetMapping("/customer-data")
    public ResponseEntity<String> fetchAndDumpCustomerSatteliteData() {
        try {
            List<CustomerSatellite> custSattelite = customerSatelliteService.fetchAndParseCustomerSatelliteData();
            customerSatelliteService.customerRepository.saveAll(custSattelite);
            return ResponseEntity.ok("Data fetched, parsed, and dumped successfully.");
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
            return ResponseEntity.status(500).body("Error occurred while fetching and dumping data.");
        }
    }

    @GetMapping("get")
    public List<CustomerSatellite> getAll() {
        return customerSatelliteService.getAll();
    }

    @GetMapping("get/{id}")
    public ResponseEntity<CustomerSatellite> getById(@PathVariable String id) {
        CustomerSatellite launcher = customerSatelliteService.getById(id);
        if (launcher == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(launcher);
    }

    @PostMapping("insert")
    public ResponseEntity<CustomerSatellite> create(@RequestBody CustomerSatellite launcher) {
        CustomerSatellite createdLauncher = customerSatelliteService.create(launcher);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLauncher);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<CustomerSatellite> update(@PathVariable String id, @RequestBody CustomerSatellite updatedLauncher) {
        CustomerSatellite launcher = customerSatelliteService.update(id, updatedLauncher);
        if (launcher == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(launcher);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        boolean deleted = customerSatelliteService.delete(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<CustomerSatellite> searchSatellites(
            @RequestParam(required = false) String id,
            @RequestParam(required = false) String launchDate,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String launcher,
            @RequestParam(required = false) Integer mass
    ) {

        return customerSatelliteService.searchSatellites(id, launchDate, country, launcher, mass);
    }



}
