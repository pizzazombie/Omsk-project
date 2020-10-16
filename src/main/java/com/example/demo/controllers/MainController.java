package com.example.demo.controllers;


import com.example.demo.entity.Device;
import com.example.demo.entity.Project;
import com.example.demo.repository.DeviceRepository;
import com.example.demo.repository.ProjectRepository;
import com.mysql.cj.xdevapi.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller // This means that this class is a Controller

public class MainController {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private DeviceRepository deviceRepository;


    @GetMapping(path="/getDevices")
    public @ResponseBody String getDevices(@RequestParam int id) {
        // This returns a JSON or XML with the users
//        Project project = (Project) projectRepository.findAllById(Collections.singleton(id));

        List<Device> devices;
        devices = deviceRepository.findByProject_id(id);
//        try {
//            devices = deviceRepository.findByProject_id(id);
//        }catch (Exception e){
//            System.out.println("Error while loading devices");
//            e.printStackTrace();
//            return null;
//        }
//
//        return devices;
        return devices.toString();
    }
    @GetMapping(path="/getProjects")
    public @ResponseBody Iterable<Project> getProjects() {
        // This returns a JSON or XML with the users
        Iterable<Project> projects;
        try {
            projects = projectRepository.findAll();
        }catch (Exception e){
            System.out.println("Error while loading projects");
            e.printStackTrace();
            return null;
        }
        return projects;
    }
}