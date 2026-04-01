package com.hrd.springhomework03.mapper;

import com.hrd.springhomework03.Model.Entity.Venue;
import com.hrd.springhomework03.Model.Request.RequestVenue;
import com.hrd.springhomework03.Model.Response.ResponseVenue;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VenueMapper {
    Venue requestVenueToEntity(RequestVenue requestVenue);

    RequestVenue entityToRequestVenue(Venue venue);

    ResponseVenue entityToResponseVenue(Venue venue);

    Venue responseVenueToEntity(ResponseVenue responseVenue);

    List<ResponseVenue> entitiesToResponseVenues(List<Venue> venues);

    List<Venue> requestVenuesToEntities(List<RequestVenue> requestVenues);
}
