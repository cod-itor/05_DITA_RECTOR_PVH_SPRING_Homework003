package com.hrd.springhomework03.Service;

import com.hrd.springhomework03.Model.Request.RequestVenue;
import com.hrd.springhomework03.Model.Response.ApiResponse;
import com.hrd.springhomework03.Model.Response.ResponseVenue;

import java.util.List;

public interface VenueService {

    ApiResponse<List<ResponseVenue>> getAllVenue(Integer page, Integer size);

    ApiResponse<ResponseVenue> getVenueById(Long venueId);

    ApiResponse<ResponseVenue> createVenue(RequestVenue requestVenue);

    ApiResponse<ResponseVenue> updateVenue(Long venueId, RequestVenue requestVenue);

    ApiResponse<Void> deleteVenue(Long venueId);
}

