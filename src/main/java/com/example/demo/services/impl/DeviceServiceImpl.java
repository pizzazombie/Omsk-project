package com.example.demo.services.impl;

import com.example.demo.entity.Device;
import com.example.demo.entity.Event;
import com.example.demo.entity.Project;
import com.example.demo.repository.DeviceRepository;
import com.example.demo.services.DeviceService;
import com.example.demo.services.EventService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {


    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private EventService eventService;

    @Override
    public Device addDevice(Device device) {
        Device savedDevice = deviceRepository.save(device);

        return savedDevice;
    }

    @Override
    public void delete(int id) {
        deviceRepository.delete(deviceRepository.getById(id));
    }

    @Override
    public Iterable<Device> getAllByProject_Id(int id) {
        return deviceRepository.findAllByProject_id(id);
    }

    @Override
    public Device editDevice(Device device) {
        return deviceRepository.save(device);
    }

    @Override
    public List<Device> getAll() {
        return (List<Device>) deviceRepository.findAll();
    }

    //count errors/warnings and events
    @Override
    public HashMap<String, Integer> findAllByEventTypeError(int device_id){
        Iterable<Event> events = eventService.getAllByDevice_id(device_id);

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        int errors = 0;
        int stable = 0;
        Timestamp date;
        for (Event e : events){
             date = e.getDate();
             if (e.getType().equals("event"))
                 stable++;
            if (System.currentTimeMillis() - date.getTime() < 3600 * 24)  //check for last 24 hours
                if (e.getType().equals("error") || e.getType().equals("warning"))
                    errors++;


        }
        map.put("events", stable);
        map.put("errors", errors);
        return map;
    }
}
