package com.example.demo.controllers;


import com.example.demo.entity.Device;

import com.example.demo.entity.Project;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.services.DeviceService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private DeviceService deviceService;

    @GetMapping(path="/getProjects", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getProjects() {
//         This returns a JSON or XML with the users
        Iterable<Project> projects;
        try {
            projects = projectRepository.findAll();
        }catch (Exception e){
            System.out.println("Error while loading projects");
            e.printStackTrace();
            return null;
        }
        JSONArray array = new JSONArray();


        for (Project project : projects){
            int id = project.getId();
            String name = project.getName();
            JSONObject JsonProject = new JSONObject();
            JSONObject stats = new JSONObject();

            List<Device> devices = (List<Device>) deviceService.getAllByProject_Id(id);
            List<String> devNames = new ArrayList<>();
            int errors = 0;
            int events = 0;
            for(Device d : devices){
                devNames.add(d.getSerial_number());
                HashMap<String, Integer> map = deviceService.findAllByEventTypeError(d.getId());
                errors += map.get("errors");
                events += map.get("events");
            }
            JsonProject.put("devices", devNames.toString());


            stats.put("deviceCount", devices.size());
            stats.put("deviceWithErrors", errors);
            stats.put("stableDevices", events);

            JsonProject.put("id", id);
            JsonProject.put("projectName", name);
            JsonProject.put("stats", stats);

            array.put(JsonProject);
        }

        return new ResponseEntity<>(array.toList(), HttpStatus.OK);
    }
}

