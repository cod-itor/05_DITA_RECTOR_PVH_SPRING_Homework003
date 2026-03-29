package com.hrd.springhomework03.Controller;

import com.hrd.springhomework03.Model.Entity.Venue;
import com.hrd.springhomework03.Model.Request.RequestVenue;
import com.hrd.springhomework03.Model.Response.ApiResponse;
import com.hrd.springhomework03.Service.VenueService;
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
@RequestMapping("/api/v1/venues")
public class VenueController {
    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @Operation(summary = "Get All venues")
    @GetMapping
    public ResponseEntity<ApiResponse<List<Venue>>> getAllVenue(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        ApiResponse<List<Venue>> response = venueService.getAllVenue(page, size);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Get venue by ID")
    @GetMapping("{venue-id}")
    public ResponseEntity<ApiResponse<Venue>> getVenueById(@PathVariable("venue-id") Long venueId) {
        ApiResponse<Venue> response = venueService.getVenueById(venueId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Create A New Venue")
    @PostMapping
    public ResponseEntity<ApiResponse<Venue>> createVenue(
            @RequestBody RequestVenue requestVenue) {
        ApiResponse<Venue> response = venueService.createVenue(requestVenue);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Update Venue By ID")
    @PutMapping("{venue-id}")
    public ResponseEntity<ApiResponse<Venue>> updateVenue(
            @PathVariable("venue-id") Long venueId,
            @RequestBody RequestVenue requestVenue) {
        ApiResponse<Venue> response = venueService.updateVenue(venueId, requestVenue);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @Operation(summary = "Delete venue by ID")
    @DeleteMapping("{venue-id}")
    public ResponseEntity<ApiResponse<Void>> deleteVenue(@PathVariable("venue-id") Long venueId) {
        ApiResponse<Void> response = venueService.deleteVenue(venueId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
