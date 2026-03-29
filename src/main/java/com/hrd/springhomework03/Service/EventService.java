package com.hrd.springhomework03.Service;

import com.hrd.springhomework03.Model.Entity.Events;
import com.hrd.springhomework03.Model.Request.RequestEvent;
import com.hrd.springhomework03.Model.Response.ApiResponse;

import java.util.List;

public interface EventService {
    ApiResponse<List<Events>> getAllEvents(Integer page, Integer size);

    ApiResponse<Events> getEventById(Long eventId);

    ApiResponse<Events> createEvent(RequestEvent requestEvent);

    ApiResponse<Events> updateEvent(Long eventId, RequestEvent requestEvent);

    ApiResponse<Void> deleteEvent(Long eventId);
}
