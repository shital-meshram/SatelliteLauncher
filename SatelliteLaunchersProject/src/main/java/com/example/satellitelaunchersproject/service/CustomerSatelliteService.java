package com.example.satellitelaunchersproject.service;

import com.example.satellitelaunchersproject.dto.CustomerSatelliteResponse;
import com.example.satellitelaunchersproject.pojo.CustomerSatellite;
import com.example.satellitelaunchersproject.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class CustomerSatelliteService {

    private String CUSTOMER_API_URL = "https://isro.vercel.app/api/customer_satellites";

    @Autowired
    public  RestTemplate restTemplate;
    @Autowired
    public  ObjectMapper objectMapper;
    @Autowired
    public CustomerRepository customerRepository;
    public List<CustomerSatellite> fetchAndParseCustomerSatelliteData() throws IOException {
        String launchersData = restTemplate.getForObject(CUSTOMER_API_URL, String.class);
        CustomerSatelliteResponse launcherApiResponse = objectMapper.readValue(launchersData, CustomerSatelliteResponse.class);
        return Arrays.asList(launcherApiResponse.getLaunchers());
    }

    public List<CustomerSatellite> getAll() {
        return customerRepository.findAll();
    }

    public CustomerSatellite getById(String id) {
        return customerRepository.findById(id).orElse(null);
    }

    public CustomerSatellite create(CustomerSatellite launcher) {
        return customerRepository.save(launcher);
    }

    public CustomerSatellite update(String id, CustomerSatellite updatedLauncher) {
        if (!customerRepository.existsById(id)) {
            return null;
        }
        updatedLauncher.setId(id);
        return customerRepository.save(updatedLauncher);
    }

    public boolean delete(String id) {
        if (!customerRepository.existsById(id)) {
            return false;
        }
        customerRepository.deleteById(id);
        return true;
    }

    public List<CustomerSatellite> searchSatellites(String id, String launchDate, String country, String launcher, Integer mass) {
        return customerRepository.search(id, launchDate, country, launcher, mass);
    }
}
