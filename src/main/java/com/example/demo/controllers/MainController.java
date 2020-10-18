package com.example.demo.controllers;


import com.example.demo.entity.Device;
import com.example.demo.entity.Event;
import com.example.demo.entity.Project;
import com.example.demo.repository.DeviceRepository;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.services.DeviceService;
import com.example.demo.services.EventService;
import com.mysql.cj.xdevapi.JsonArray;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller // This means that this class is a Controller

public class MainController {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventService eventService;

    @Autowired
    private DeviceService deviceService;


    @GetMapping(path="/putObjects")
    public @ResponseBody String putProjects() {
        // This returns a JSON or XML with the users


        for(int i=1; i<3; i++)
        {
            deviceService.addDevice(new Device());
        }
            return "ok";
    }

    @GetMapping(path="/getDevices", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDevices(@RequestParam int id) {
        // This returns a JSON or XML with the users
//        Project project = (Project) projectRepository.findAllById(Collections.singleton(id));
        JSONObject array = new JSONObject();
//        Iterable<Device> devices = null;
        List<Device> devices = (List<Device>) deviceService.getAllByProject_Id(id);

        JSONObject jsonDevice = new JSONObject();
        for (Device d : devices){

            JSONObject info = new JSONObject();
            info.put("id", d.getId());
            info.put("serialNumber", d.getSerial_number());
            info.put("projectId", d.getProject_id());

            JSONObject summaryInfo = new JSONObject();
            List<Event> events = (List<Event>) eventService.getAllByDevice_id(d.getId());
//            System.out.println(eventRepository.getById(1).getType());
//            Iterable<Event> events = eventRepository.findAll();
            int eventCount = 0;
            int errorCount = 0;
            int warningCount = 0;
            for (Event e : events){
                if (e.getType().equals("event"))
                    eventCount++;
                else if (e.getType().equals("error"))
                    errorCount++;
                else if (e.getType().equals("warning"))
                    warningCount++;
            }
            summaryInfo.put("eventCount", eventCount);
            summaryInfo.put("errorCount", errorCount);
            summaryInfo.put("warningCount", warningCount);

            info.put("hasError", errorCount != 0);
            info.put("summaryInfo", summaryInfo);


            jsonDevice.put(d.getSerial_number(), info);
//            array.put(d.getSerial_number(), jsonDevice);
        }




        return new ResponseEntity<>(jsonDevice.toMap(), HttpStatus.OK);
    }
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
            for(Device d : devices){
                devNames.add(d.getSerial_number());
            }
            JsonProject.put("devices", devNames.toString());
            stats.put("deviceCount", devices.size());

            JsonProject.put("id", id);
            JsonProject.put("projectName", name);
            JsonProject.put("stats", stats);

            array.put(JsonProject);
        }

        return new ResponseEntity<>(array.toList(), HttpStatus.OK);
    }
}