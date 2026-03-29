package com.hrd.springhomework03.Controller;

import com.hrd.springhomework03.Model.Entity.Events;
import com.hrd.springhomework03.Model.Request.RequestEvent;
import com.hrd.springhomework03.Model.Response.ApiResponse;
import com.hrd.springhomework03.Service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @Operation(summary = "Get All events")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Events>>> getAllEvents(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        ApiResponse<List<Events>> response = eventService.getAllEvents(page, size);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Get event by ID")
    @GetMapping("{event-id}")
    public ResponseEntity<ApiResponse<Events>> getEventById(@PathVariable("event-id") Long eventId) {
        ApiResponse<Events> response = eventService.getEventById(eventId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Create A New Event")
    @PostMapping
    public ResponseEntity<ApiResponse<Events>> createEvent(
            @RequestBody RequestEvent requestEvent) {
        ApiResponse<Events> response = eventService.createEvent(requestEvent);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Update Event By ID")
    @PutMapping("{event-id}")
    public ResponseEntity<ApiResponse<Events>> updateEvent(
            @PathVariable("event-id") Long eventId,
            @RequestBody RequestEvent requestEvent) {
        ApiResponse<Events> response = eventService.updateEvent(eventId, requestEvent);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Delete event by ID")
    @DeleteMapping("{event-id}")
    public ResponseEntity<ApiResponse<Void>> deleteEvent(@PathVariable("event-id") Long eventId) {
        ApiResponse<Void> response = eventService.deleteEvent(eventId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }




}
