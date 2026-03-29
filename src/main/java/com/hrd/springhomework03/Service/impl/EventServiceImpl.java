package com.hrd.springhomework03.Service.impl;

import com.hrd.springhomework03.Model.Entity.Events;
import com.hrd.springhomework03.Model.Request.RequestEvent;
import com.hrd.springhomework03.Model.Response.ApiResponse;
import com.hrd.springhomework03.Repository.EventRepository;
import com.hrd.springhomework03.Service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public ApiResponse<List<Events>> getAllEvents(Integer page, Integer size) {
        Integer offset = size * (page - 1);
        List<Events> events = eventRepository.getAllEvents(offset, size);
        return ApiResponse.<List<Events>>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .messages("Events retrieved successfully")
                .payload(events)
                .timestamp(Instant.now())
                .build();
    }

    @Override
    public ApiResponse<Events> getEventById(Long eventId) {
        Events event = eventRepository.getEventById(eventId);

        if (event != null) {
            return ApiResponse.<Events>builder()
                    .success(true)
                    .status(HttpStatus.OK.value())
                    .messages("Event fetched successfully")
                    .payload(event)
                    .timestamp(Instant.now())
                    .build();
        }
        return ApiResponse.<Events>builder()
                .success(false)
                .status(HttpStatus.NOT_FOUND.value())
                .messages("No event found with the given ID")
                .timestamp(Instant.now())
                .build();
    }

    @Override
    public ApiResponse<Events> createEvent(RequestEvent requestEvent) {
        Events created = eventRepository.createEvent(requestEvent);

        if (created == null) {
            return ApiResponse.<Events>builder()
                    .success(false)
                    .status(HttpStatus.BAD_REQUEST.value())
                    .messages("Failed to create event")
                    .payload(null)
                    .timestamp(Instant.now())
                    .build();
        }

        return ApiResponse.<Events>builder()
                .success(true)
                .status(HttpStatus.CREATED.value())
                .messages("Event created successfully")
                .payload(created)
                .timestamp(Instant.now())
                .build();
    }

    @Override
    public ApiResponse<Events> updateEvent(Long eventId, RequestEvent requestEvent) {
        Events event = eventRepository.getEventById(eventId);

        if (event == null) {
            return ApiResponse.<Events>builder()
                    .success(false)
                    .status(HttpStatus.NOT_FOUND.value())
                    .messages("No event found with the given ID")
                    .timestamp(Instant.now())
                    .build();
        }

        Events updated = eventRepository.updateEvent(eventId, requestEvent);

        if (updated == null) {
            return ApiResponse.<Events>builder()
                    .success(false)
                    .status(HttpStatus.BAD_REQUEST.value())
                    .messages("Failed to update event")
                    .timestamp(Instant.now())
                    .build();
        }

        return ApiResponse.<Events>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .messages("Event updated successfully")
                .payload(updated)
                .timestamp(Instant.now())
                .build();
    }

    @Override
    public ApiResponse<Void> deleteEvent(Long eventId) {
        Events event = eventRepository.getEventById(eventId);

        if (event == null) {
            return ApiResponse.<Void>builder()
                    .success(false)
                    .status(HttpStatus.NOT_FOUND.value())
                    .messages("No event found with the given ID")
                    .timestamp(Instant.now())
                    .build();
        }

        int rowsDeleted = eventRepository.deleteEvent(eventId);

        if (rowsDeleted > 0) {
            return ApiResponse.<Void>builder()
                    .success(true)
                    .status(HttpStatus.OK.value())
                    .messages("Event deleted successfully")
                    .timestamp(Instant.now())
                    .build();
        }
        return ApiResponse.<Void>builder()
                .success(false)
                .status(HttpStatus.NOT_FOUND.value())
                .messages("No event found with the given ID")
                .timestamp(Instant.now())
                .build();
    }
}

