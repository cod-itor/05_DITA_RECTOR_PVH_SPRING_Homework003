package com.hrd.springhomework03.mapper;

import com.hrd.springhomework03.Model.Entity.Events;
import com.hrd.springhomework03.Model.Request.RequestEvent;
import com.hrd.springhomework03.Model.Response.ResponseEvent;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventMapper {
    Events requestEventToEntity(RequestEvent requestEvent);

    RequestEvent entityToRequestEvent(Events events);

    ResponseEvent entityToResponseEvent(Events events);

    Events responseEventToEntity(ResponseEvent responseEvent);

    List<ResponseEvent> entitiesToResponseEvents(List<Events> events);

    List<Events> requestEventsToEntities(List<RequestEvent> requestEvents);
}

