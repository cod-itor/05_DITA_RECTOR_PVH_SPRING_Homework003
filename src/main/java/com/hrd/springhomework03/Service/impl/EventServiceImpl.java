package com.hrd.springhomework03.Service.impl;

import com.hrd.springhomework03.Exception.NotFoundException;
import com.hrd.springhomework03.Model.Entity.Events;
import com.hrd.springhomework03.Repository.EventRepository;
import com.hrd.springhomework03.Service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Events> getAllEvents(Integer page, Integer size) {
        Integer offset = (page - 1) * size;
        return eventRepository.getAllEvents(offset, size);
    }

    @Override
    public Events getEventById(Long eventId) {
        return eventRepository.getEventById(eventId)
                .orElseThrow(() -> new NotFoundException("Event with ID " + eventId + " not found"));
    }

    @Override
    public void createEvent(Events event) {
        eventRepository.createEvent(event);
    }

    @Override
    public void updateEvent(Long eventId, Events event) {
        Events existingEvent = getEventById(eventId);
        event.setEventId(eventId);
        int result = eventRepository.updateEvent(event);
        if (result == 0) {
            throw new NotFoundException("Failed to update event with ID " + eventId);
        }
    }

    @Override
    public void deleteEvent(Long eventId) {
        Events event = getEventById(eventId);
        int result = eventRepository.deleteEvent(eventId);
        if (result == 0) {
            throw new NotFoundException("Failed to delete event with ID " + eventId);
        }
    }




}
