package com.hrd.springhomework03.Service.impl;

import com.hrd.springhomework03.Exception.BadRequestException;
import com.hrd.springhomework03.Exception.NotFoundException;
import com.hrd.springhomework03.Model.Entity.Venue;
import com.hrd.springhomework03.Model.Request.RequestVenue;
import com.hrd.springhomework03.Model.Response.ApiResponse;
import com.hrd.springhomework03.Model.Response.ResponseVenue;
import com.hrd.springhomework03.Repository.VenueRepository;
import com.hrd.springhomework03.Service.VenueService;
import com.hrd.springhomework03.mapper.VenueMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;
    private final VenueMapper venueMapper;

    @Override
    public ApiResponse<List<ResponseVenue>> getAllVenue(Integer page, Integer size) {
        Integer offset = size * (page - 1);
        List<Venue> venues = venueRepository.getAllVenue(offset, size);
        List<ResponseVenue> responseVenues = venueMapper.entitiesToResponseVenues(venues);
        return ApiResponse.<List<ResponseVenue>>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .messages("Venues retrieved successfully")
                .payload(responseVenues)
                .timestamp(Instant.now())
                .build();
    }

    @Override
    public ApiResponse<ResponseVenue> getVenueById(Long venueId) {
        Venue venue = venueRepository.getVenueById(venueId);

        if (venue == null) {
            throw new NotFoundException("No venue found with the given ID: " + venueId);
        }

        ResponseVenue responseVenue = venueMapper.entityToResponseVenue(venue);
        return ApiResponse.<ResponseVenue>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .messages("Venue fetched successfully")
                .payload(responseVenue)
                .timestamp(Instant.now())
                .build();
    }

    @Override
    public ApiResponse<ResponseVenue> createVenue(RequestVenue requestVenue) {
        if (requestVenue.getVenueName() == null || requestVenue.getVenueName().trim().isEmpty()) {
            throw new BadRequestException("Venue name cannot be empty");
        }

        if (requestVenue.getLocation() == null || requestVenue.getLocation().trim().isEmpty()) {
            throw new BadRequestException("Location cannot be empty");
        }

        Venue created = venueRepository.createVenue(requestVenue);

        if (created == null) {
            throw new BadRequestException("Failed to create venue");
        }

        ResponseVenue responseVenue = venueMapper.entityToResponseVenue(created);
        return ApiResponse.<ResponseVenue>builder()
                .success(true)
                .status(HttpStatus.CREATED.value())
                .messages("Venue created successfully")
                .payload(responseVenue)
                .timestamp(Instant.now())
                .build();
    }

    @Override
    public ApiResponse<ResponseVenue> updateVenue(Long venueId, RequestVenue requestVenue) {
        Venue venue = venueRepository.getVenueById(venueId);

        if (venue == null) {
            throw new NotFoundException("No venue found with the given ID: " + venueId);
        }

        if (requestVenue.getVenueName() == null || requestVenue.getVenueName().trim().isEmpty()) {
            throw new BadRequestException("Venue name cannot be empty");
        }

        if (requestVenue.getLocation() == null || requestVenue.getLocation().trim().isEmpty()) {
            throw new BadRequestException("Location cannot be empty");
        }

        Venue updated = venueRepository.updateVenue(venueId, requestVenue);

        if (updated == null) {
            throw new BadRequestException("Failed to update venue");
        }

        ResponseVenue responseVenue = venueMapper.entityToResponseVenue(updated);
        return ApiResponse.<ResponseVenue>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .messages("Venue updated successfully")
                .payload(responseVenue)
                .timestamp(Instant.now())
                .build();
    }

    @Override
    public ApiResponse<Void> deleteVenue(Long venueId) {
        Venue venue = venueRepository.getVenueById(venueId);

        if (venue == null) {
            throw new NotFoundException("No venue found with the given ID: " + venueId);
        }

        int rowsDeleted = venueRepository.deleteVenue(venueId);

        if (rowsDeleted <= 0) {
            throw new BadRequestException("Failed to delete venue");
        }

        return ApiResponse.<Void>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .messages("Venue deleted successfully")
                .timestamp(Instant.now())
                .build();
    }
}
