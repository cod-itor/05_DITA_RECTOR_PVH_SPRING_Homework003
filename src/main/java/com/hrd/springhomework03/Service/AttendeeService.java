package com.hrd.springhomework03.Service;

import com.hrd.springhomework03.Model.Request.RequestAttendees;
import com.hrd.springhomework03.Model.Response.ApiResponse;
import com.hrd.springhomework03.Model.Response.ResponseAttendees;

import java.util.List;

public interface AttendeeService {
    ApiResponse<List<ResponseAttendees>> getAllAttendees(Integer page, Integer size);
    ApiResponse<ResponseAttendees> getAttendeeById(Long attendeeId);
    ApiResponse<ResponseAttendees> createAttendee(RequestAttendees requestAttendees);
    ApiResponse<ResponseAttendees> updateAttendee(Long attendeeId, RequestAttendees requestAttendees);
    ApiResponse<Void> deleteAttendee(Long attendeeId);
}
