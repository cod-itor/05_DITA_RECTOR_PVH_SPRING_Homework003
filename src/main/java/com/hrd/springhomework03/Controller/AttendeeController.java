package com.hrd.springhomework03.Controller;

import com.hrd.springhomework03.Model.Request.RequestAttendees;
import com.hrd.springhomework03.Model.Response.ApiResponse;
import com.hrd.springhomework03.Model.Response.ResponseAttendees;
import com.hrd.springhomework03.Service.AttendeeService;
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
@RequestMapping("/api/v1/attendees")
public class AttendeeController {
    private final AttendeeService attendeeService;

    public AttendeeController(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }

    @Operation(summary = "Get All attendees")
    @GetMapping
    public ResponseEntity<ApiResponse<List<ResponseAttendees>>> getAllAttendees(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        ApiResponse<List<ResponseAttendees>> response = attendeeService.getAllAttendees(page, size);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Get attendee by ID")
    @GetMapping("{attendee-id}")
    public ResponseEntity<ApiResponse<ResponseAttendees>> getAttendeeById(@PathVariable("attendee-id") Long attendeeId) {
        ApiResponse<ResponseAttendees> response = attendeeService.getAttendeeById(attendeeId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Create A New Attendee")
    @PostMapping
    public ResponseEntity<ApiResponse<ResponseAttendees>> createAttendee(
            @RequestBody RequestAttendees requestAttendees) {
        ApiResponse<ResponseAttendees> response = attendeeService.createAttendee(requestAttendees);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Update Attendee By ID")
    @PutMapping("{attendee-id}")
    public ResponseEntity<ApiResponse<ResponseAttendees>> updateAttendee(
            @PathVariable("attendee-id") Long attendeeId,
            @RequestBody RequestAttendees requestAttendees) {
        ApiResponse<ResponseAttendees> response = attendeeService.updateAttendee(attendeeId, requestAttendees);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Delete attendee by ID")
    @DeleteMapping("{attendee-id}")
    public ResponseEntity<ApiResponse<Void>> deleteAttendee(@PathVariable("attendee-id") Long attendeeId) {
        ApiResponse<Void> response = attendeeService.deleteAttendee(attendeeId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
