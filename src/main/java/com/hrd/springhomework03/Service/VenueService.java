package com.hrd.springhomework03.Service;

import com.hrd.springhomework03.Model.Entity.Venue;
import com.hrd.springhomework03.Model.Request.RequestVenue;
import com.hrd.springhomework03.Model.Response.ApiResponse;

import java.util.List;

public interface VenueService {

    ApiResponse<List<Venue>> getAllVenue(Integer page, Integer size);

    ApiResponse<Venue> getVenueById(Long venueId);

    ApiResponse<Venue> createVenue(RequestVenue requestVenue);

    ApiResponse<Venue> updateVenue(Long venueId, RequestVenue requestVenue);

    ApiResponse<Void> deleteVenue(Long venueId);
}
