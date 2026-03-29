package com.hrd.springhomework03.Controller;

import com.hrd.springhomework03.Model.Entity.Events;
import com.hrd.springhomework03.Service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
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

    @Operation(summary = "Get all events")
    @GetMapping
    public ResponseEntity<List<Events>> getAllEvents(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        List<Events> listEvents = eventService.getAllEvents(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(listEvents);
    }

    @Operation(summary = "Get event by id")
    @GetMapping("/{eventId}")
    public ResponseEntity<Events> getEventById(@PathVariable Long eventId) {
        Events event = eventService.getEventById(eventId);
        return ResponseEntity.status(HttpStatus.OK).body(event);
    }

    @Operation(summary = "Create new event")
    @PostMapping
    public ResponseEntity<String> createEvent(@RequestBody Events event) {
        eventService.createEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body("Event created successfully");
    }

    @Operation(summary = "Update event")
    @PutMapping("/{eventId}")
    public ResponseEntity<String> updateEvent(
            @PathVariable Long eventId,
            @RequestBody Events event) {
        eventService.updateEvent(eventId, event);
        return ResponseEntity.status(HttpStatus.OK).body("Event updated successfully");
    }

    @Operation(summary = "Delete event")
    @DeleteMapping("/{eventId}")
    public ResponseEntity<String> deleteEvent(@PathVariable Long eventId) {
        eventService.deleteEvent(eventId);
        return ResponseEntity.status(HttpStatus.OK).body("Event deleted successfully");
    }




}
