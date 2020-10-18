package com.example.demo.repository;

import com.example.demo.entity.Device;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface DeviceRepository  extends CrudRepository<Device, Integer> {
    Iterable<Device> findAllByProject_id(int projectId);

    Device getById(int id);

    //    @Query("select b from Event b where b.device_id = :device_id")

}
