package com.example.demo.repository;

import com.example.demo.entity.Device;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface DeviceRepository  extends CrudRepository<Device, Integer> {
    List<Device> findByProject_id(int id);

}
