package com.hrd.springhomework03.Service;

import com.hrd.springhomework03.Model.Request.RequestEvent;
import com.hrd.springhomework03.Model.Response.ApiResponse;
import com.hrd.springhomework03.Model.Response.ResponseEvent;

import java.util.List;

public interface EventService {
    ApiResponse<List<ResponseEvent>> getAllEvents(Integer page, Integer size);

    ApiResponse<ResponseEvent> getEventById(Long eventId);

    ApiResponse<ResponseEvent> createEvent(RequestEvent requestEvent);

    ApiResponse<ResponseEvent> updateEvent(Long eventId, RequestEvent requestEvent);

    ApiResponse<Void> deleteEvent(Long eventId);
}

