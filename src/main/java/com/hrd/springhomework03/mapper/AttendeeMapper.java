package com.hrd.springhomework03.mapper;

import com.hrd.springhomework03.Model.Entity.Attendees;
import com.hrd.springhomework03.Model.Request.RequestAttendees;
import com.hrd.springhomework03.Model.Response.ResponseAttendees;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AttendeeMapper {
    Attendees requestAttendeesToEntity(RequestAttendees requestAttendees);

    RequestAttendees entityToRequestAttendees(Attendees attendees);

    ResponseAttendees entityToResponseAttendees(Attendees attendees);

    Attendees responseAttendeesToEntity(ResponseAttendees responseAttendees);

    List<ResponseAttendees> entitiesToResponseAttendees(List<Attendees> attendees);

    List<Attendees> requestAttendeesToEntities(List<RequestAttendees> requestAttendees);
}
