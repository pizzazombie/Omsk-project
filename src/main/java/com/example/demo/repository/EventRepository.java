
package com.example.demo.repository;


import com.example.demo.entity.Device;
import com.example.demo.entity.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface EventRepository  extends CrudRepository<Event, Integer> {
//    Iterable<Event> findAllByDevice_id(int id);
//    Iterable<Event> findByDevice_id(int id);
//
    Event getById(int id);
    Iterable<Event> getAllByDevice_id(int id);
//    @Query("select b from Event b where b.device_id = :device_id")
//    Iterable<Event> findAllByDevice_id(@Param("device_id") int device_id);

}