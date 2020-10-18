package com.example.demo.services;

import com.example.demo.entity.Device;
import com.example.demo.entity.Event;

public interface EventService {
//    Event addEvent(Event event);
    public Iterable<Event> getAllByDevice_id(int device_id);
}
