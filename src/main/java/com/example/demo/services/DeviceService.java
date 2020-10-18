package com.example.demo.services;

import com.example.demo.entity.Device;
import com.example.demo.entity.Project;

public interface DeviceService {

    Device addDevice(Device device);
    void delete(int id);
//    Iterable<Device> getAllByProject(Project project);
    public Iterable<Device> getAllByProject_Id(int id);
    Device editDevice(Device device);
    Iterable<Device> getAll();

    public Iterable<Device> findAllByEventTypeError(int device_id);


}
