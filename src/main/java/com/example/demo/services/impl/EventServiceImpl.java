package com.example.demo.services.impl;

import com.example.demo.entity.Event;
import com.example.demo.repository.EventRepository;
import com.example.demo.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EventServiceImpl implements EventService {
    @Autowired
    EventRepository eventRepository;

    public Iterable<Event> getAllByDevice_id(int device_id){
        Iterable<Event> events = eventRepository.getAllByDevice_id(device_id);
        return events;
}
}
