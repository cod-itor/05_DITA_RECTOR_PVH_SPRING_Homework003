package com.hrd.springhomework03.Service.impl;

import com.hrd.springhomework03.Model.Entity.Venue;
import com.hrd.springhomework03.Model.Request.RequestVenue;
import com.hrd.springhomework03.Model.Response.ApiResponse;
import com.hrd.springhomework03.Repository.VenueRepository;
import com.hrd.springhomework03.Service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;

    public VenueServiceImpl(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    public ApiResponse<List<Venue>> getAllVenue(Integer page, Integer size) {
        Integer offset = size * (page - 1);
        List<Venue> venues = venueRepository.getAllVenue(offset, size);
        return ApiResponse.<List<Venue>>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .messages("Venues retrieved successfully")
                .payload(venues)
                .timestamp(Instant.now())
                .build();
    }

    @Override
    public ApiResponse<Venue> getVenueById(Long venueId) {
        Venue venue = venueRepository.getVenueById(venueId);

        if (venue != null) {
            return ApiResponse.<Venue>builder()
                    .success(true)
                    .status(HttpStatus.OK.value())
                    .messages("Venue fetched successfully")
                    .payload(venue)
                    .timestamp(Instant.now())
                    .build();
        }
        return ApiResponse.<Venue>builder()
                .success(false)
                .status(HttpStatus.NOT_FOUND.value())
                .messages("No venue found with the given ID")
                .timestamp(Instant.now())
                .build();
    }

    @Override
    public ApiResponse<Venue> createVenue(RequestVenue requestVenue) {
        Long newVenueId = venueRepository.createVenue(requestVenue);

        if (newVenueId == null) {
            return ApiResponse.<Venue>builder()
                    .success(false)
                    .status(HttpStatus.BAD_REQUEST.value())
                    .messages("Failed to create venue")
                    .payload(null)
                    .timestamp(Instant.now())
                    .build();
        }

        Venue created = venueRepository.getVenueById(newVenueId);

        return ApiResponse.<Venue>builder()
                .success(true)
                .status(HttpStatus.CREATED.value())
                .messages("Venue created successfully")
                .payload(created)
                .timestamp(Instant.now())
                .build();
    }

    @Override
    public ApiResponse<Venue> updateVenue(Long venueId, RequestVenue requestVenue) {
        Venue venue = venueRepository.getVenueById(venueId);

        if (venue == null) {
            return ApiResponse.<Venue>builder()
                    .success(false)
                    .status(HttpStatus.NOT_FOUND.value())
                    .messages("No venue found with the given ID")
                    .timestamp(Instant.now())
                    .build();
        }

        int rows = venueRepository.updateVenue(venueId, requestVenue);

        if (rows == 0) {
            return ApiResponse.<Venue>builder()
                    .success(false)
                    .status(HttpStatus.BAD_REQUEST.value())
                    .messages("Failed to update venue")
                    .timestamp(Instant.now())
                    .build();
        }

        Venue updated = venueRepository.getVenueById(venueId);
        return ApiResponse.<Venue>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .messages("Venue updated successfully")
                .payload(updated)
                .timestamp(Instant.now())
                .build();
    }

    @Override
    public ApiResponse<Void> deleteVenue(Long venueId) {
        Venue venue = venueRepository.getVenueById(venueId);

        if (venue == null) {
            return ApiResponse.<Void>builder()
                    .success(false)
                    .status(HttpStatus.NOT_FOUND.value())
                    .messages("No venue found with the given ID")
                    .timestamp(Instant.now())
                    .build();
        }

        int rowsDeleted = venueRepository.deleteVenue(venueId);

        if (rowsDeleted > 0) {
            return ApiResponse.<Void>builder()
                    .success(true)
                    .status(HttpStatus.OK.value())
                    .messages("Venue deleted successfully")
                    .timestamp(Instant.now())
                    .build();
        }
        return ApiResponse.<Void>builder()
                .success(false)
                .status(HttpStatus.NOT_FOUND.value())
                .messages("No venue found with the given ID")
                .timestamp(Instant.now())
                .build();
    }
}
