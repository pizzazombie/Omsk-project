
package com.example.demo.repository;


import com.example.demo.entity.Device;
import com.example.demo.entity.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;



public interface EventRepository  extends CrudRepository<Event, Integer> {

    Event getById(int id);
    Iterable<Event> getAllByDevice_id(int id);

}