package com.example.satellitelaunchersproject.service;


import com.example.satellitelaunchersproject.dto.LauncherApiResponse;
import com.example.satellitelaunchersproject.pojo.SatelliteLauncher;
import com.example.satellitelaunchersproject.repository.LauncherRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class LauncherService {

    private String LAUNCHERS_API_URL = "https://isro.vercel.app/api/launchers";

    @Autowired
    private  RestTemplate restTemplate;
    @Autowired
    private  ObjectMapper objectMapper;

    @Autowired
    public  LauncherRepository launcherRepository;



    public List<SatelliteLauncher> fetchAndParseLaunchersData() throws IOException {
        String launchersData = restTemplate.getForObject(LAUNCHERS_API_URL, String.class);
        LauncherApiResponse launcherApiResponse = objectMapper.readValue(launchersData, LauncherApiResponse.class);
        return Arrays.asList(launcherApiResponse.getLaunchers());
    }

    public List<SatelliteLauncher> getAll() {
        return launcherRepository.findAll();
    }

    public SatelliteLauncher getById(String id) {
        return launcherRepository.findById(id).orElse(null);
    }

    public SatelliteLauncher create(SatelliteLauncher launcher) {
        return launcherRepository.save(launcher);
    }

    public SatelliteLauncher update(String id, SatelliteLauncher updatedLauncher) {
        if (!launcherRepository.existsById(id)) {
            return null;
        }
        updatedLauncher.setId(id);
        return launcherRepository.save(updatedLauncher);
    }

    public boolean delete(String id) {
        if (!launcherRepository.existsById(id)) {
            return false;
        }
        launcherRepository.deleteById(id);
        return true;
    }
}
