package com.hrd.springhomework03.Service.impl;

import com.hrd.springhomework03.Exception.BadRequestException;
import com.hrd.springhomework03.Exception.InvalidForeignKeyException;
import com.hrd.springhomework03.Exception.NotFoundException;
import com.hrd.springhomework03.Model.Entity.Events;
import com.hrd.springhomework03.Model.Request.RequestEvent;
import com.hrd.springhomework03.Model.Response.ApiResponse;
import com.hrd.springhomework03.Model.Response.ResponseEvent;
import com.hrd.springhomework03.Repository.EventRepository;
import com.hrd.springhomework03.Repository.VenueRepository;
import com.hrd.springhomework03.Service.EventService;
import com.hrd.springhomework03.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final VenueRepository venueRepository;
    private final EventMapper eventMapper;

    @Override
    public ApiResponse<List<ResponseEvent>> getAllEvents(Integer page, Integer size) {
        Integer offset = size * (page - 1);
        List<Events> events = eventRepository.getAllEvents(offset, size);
        List<ResponseEvent> responseEvents = eventMapper.entitiesToResponseEvents(events);
        return ApiResponse.<List<ResponseEvent>>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .messages("Events retrieved successfully")
                .payload(responseEvents)
                .timestamp(Instant.now())
                .build();
    }

    @Override
    public ApiResponse<ResponseEvent> getEventById(Long eventId) {
        Events event = eventRepository.getEventById(eventId);

        if (event == null) {
            throw new NotFoundException("No event found with the given ID: " + eventId);
        }

        ResponseEvent responseEvent = eventMapper.entityToResponseEvent(event);
        return ApiResponse.<ResponseEvent>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .messages("Event fetched successfully")
                .payload(responseEvent)
                .timestamp(Instant.now())
                .build();
    }

    @Override
    public ApiResponse<ResponseEvent> createEvent(RequestEvent requestEvent) {
        if (requestEvent.getEventName() == null || requestEvent.getEventName().trim().isEmpty()) {
            throw new BadRequestException("Event name cannot be empty");
        }

        if (requestEvent.getEventDate() == null) {
            throw new BadRequestException("Event date cannot be null");
        }

        if (requestEvent.getVenueId() == null) {
            throw new BadRequestException("Venue ID cannot be null");
        }

        if (!venueRepository.venueExists(requestEvent.getVenueId())) {
            throw new InvalidForeignKeyException("Venue with ID " + requestEvent.getVenueId() + " does not exist");
        }

        Events created = eventRepository.createEvent(requestEvent);

        if (created == null) {
            throw new BadRequestException("Failed to create event");
        }

        ResponseEvent responseEvent = eventMapper.entityToResponseEvent(created);
        return ApiResponse.<ResponseEvent>builder()
                .success(true)
                .status(HttpStatus.CREATED.value())
                .messages("Event created successfully")
                .payload(responseEvent)
                .timestamp(Instant.now())
                .build();
    }

    @Override
    public ApiResponse<ResponseEvent> updateEvent(Long eventId, RequestEvent requestEvent) {
        Events event = eventRepository.getEventById(eventId);

        if (event == null) {
            throw new NotFoundException("No event found with the given ID: " + eventId);
        }

        if (requestEvent.getEventName() == null || requestEvent.getEventName().trim().isEmpty()) {
            throw new BadRequestException("Event name cannot be empty");
        }

        if (requestEvent.getEventDate() == null) {
            throw new BadRequestException("Event date cannot be null");
        }

        if (requestEvent.getVenueId() == null) {
            throw new BadRequestException("Venue ID cannot be null");
        }

        if (!venueRepository.venueExists(requestEvent.getVenueId())) {
            throw new InvalidForeignKeyException("Venue with ID " + requestEvent.getVenueId() + " does not exist");
        }

        Events updated = eventRepository.updateEvent(eventId, requestEvent);

        if (updated == null) {
            throw new BadRequestException("Failed to update event");
        }

        ResponseEvent responseEvent = eventMapper.entityToResponseEvent(updated);
        return ApiResponse.<ResponseEvent>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .messages("Event updated successfully")
                .payload(responseEvent)
                .timestamp(Instant.now())
                .build();
    }

    @Override
    public ApiResponse<Void> deleteEvent(Long eventId) {
        Events event = eventRepository.getEventById(eventId);

        if (event == null) {
            throw new NotFoundException("No event found with the given ID: " + eventId);
        }

        int rowsDeleted = eventRepository.deleteEvent(eventId);

        if (rowsDeleted <= 0) {
            throw new BadRequestException("Failed to delete event");
        }

        return ApiResponse.<Void>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .messages("Event deleted successfully")
                .timestamp(Instant.now())
                .build();
    }
}
