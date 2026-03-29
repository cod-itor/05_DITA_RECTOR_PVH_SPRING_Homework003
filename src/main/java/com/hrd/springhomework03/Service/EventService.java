package com.hrd.springhomework03.Service;

import com.hrd.springhomework03.Model.Entity.Events;

import java.util.List;

public interface EventService {
    List<Events> getAllEvents(Integer page, Integer size);

    Events getEventById(Long eventId);

    void createEvent(Events event);

    void updateEvent(Long eventId, Events event);

    void deleteEvent(Long eventId);
}
