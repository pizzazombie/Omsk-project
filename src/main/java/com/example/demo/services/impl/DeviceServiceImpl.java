package com.example.demo.services.impl;

import com.example.demo.entity.Device;
import com.example.demo.entity.Event;
import com.example.demo.entity.Project;
import com.example.demo.repository.DeviceRepository;
import com.example.demo.services.DeviceService;
import com.example.demo.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
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

//    @Override
//    public Iterable<Device> getAllByProject(Project project) {
//        return deviceRepository.findAllByProject_id(project.getId());
//    }
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

    @Override
    public Iterable<Device> findAllByEventTypeError(int device_id){
        Iterable<Event> events = eventService.getAllByDevice_id(device_id);

        for (Event e : events){
             System.out.println(e.getDate());
        }

        return null;
    }
}