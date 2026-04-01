package com.hrd.springhomework03.Service.impl;

import com.hrd.springhomework03.Exception.BadRequestException;
import com.hrd.springhomework03.Exception.NotFoundException;
import com.hrd.springhomework03.Model.Entity.Attendees;
import com.hrd.springhomework03.Model.Request.RequestAttendees;
import com.hrd.springhomework03.Model.Response.ApiResponse;
import com.hrd.springhomework03.Model.Response.ResponseAttendees;
import com.hrd.springhomework03.Repository.AttendeeRepository;
import com.hrd.springhomework03.Service.AttendeeService;
import com.hrd.springhomework03.mapper.AttendeeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendeeServiceImpl implements AttendeeService {

    private final AttendeeRepository attendeeRepository;
    private final AttendeeMapper attendeeMapper;

    @Override
    public ApiResponse<List<ResponseAttendees>> getAllAttendees(Integer page, Integer size) {
        Integer offset = size * (page - 1);
        List<Attendees> attendees = attendeeRepository.getAllAttendees(offset, size);
        List<ResponseAttendees> responseAttendees = attendeeMapper.entitiesToResponseAttendees(attendees);
        return ApiResponse.<List<ResponseAttendees>>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .messages("Attendees retrieved successfully")
                .payload(responseAttendees)
                .timestamp(Instant.now())
                .build();
    }

    @Override
    public ApiResponse<ResponseAttendees> getAttendeeById(Long attendeeId) {
        Attendees attendee = attendeeRepository.getAttendeeById(attendeeId);

        if (attendee == null) {
            throw new NotFoundException("No attendee found with the given ID: " + attendeeId);
        }

        ResponseAttendees responseAttendees = attendeeMapper.entityToResponseAttendees(attendee);
        return ApiResponse.<ResponseAttendees>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .messages("Attendee fetched successfully")
                .payload(responseAttendees)
                .timestamp(Instant.now())
                .build();
    }

    @Override
    public ApiResponse<ResponseAttendees> createAttendee(RequestAttendees requestAttendees) {
        if (requestAttendees.getAttendeeName() == null || requestAttendees.getAttendeeName().trim().isEmpty()) {
            throw new BadRequestException("Attendee name cannot be empty");
        }

        if (requestAttendees.getEmail() == null || requestAttendees.getEmail().trim().isEmpty()) {
            throw new BadRequestException("Email cannot be empty");
        }

        Attendees created = attendeeRepository.createAttendee(requestAttendees);

        if (created == null) {
            throw new BadRequestException("Failed to create attendee");
        }

        ResponseAttendees responseAttendees = attendeeMapper.entityToResponseAttendees(created);
        return ApiResponse.<ResponseAttendees>builder()
                .success(true)
                .status(HttpStatus.CREATED.value())
                .messages("Attendee created successfully")
                .payload(responseAttendees)
                .timestamp(Instant.now())
                .build();
    }

    @Override
    public ApiResponse<ResponseAttendees> updateAttendee(Long attendeeId, RequestAttendees requestAttendees) {
        Attendees attendee = attendeeRepository.getAttendeeById(attendeeId);

        if (attendee == null) {
            throw new NotFoundException("No attendee found with the given ID: " + attendeeId);
        }

        if (requestAttendees.getAttendeeName() == null || requestAttendees.getAttendeeName().trim().isEmpty()) {
            throw new BadRequestException("Attendee name cannot be empty");
        }

        if (requestAttendees.getEmail() == null || requestAttendees.getEmail().trim().isEmpty()) {
            throw new BadRequestException("Email cannot be empty");
        }

        Attendees updated = attendeeRepository.updateAttendee(attendeeId, requestAttendees);

        if (updated == null) {
            throw new BadRequestException("Failed to update attendee");
        }

        ResponseAttendees responseAttendees = attendeeMapper.entityToResponseAttendees(updated);
        return ApiResponse.<ResponseAttendees>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .messages("Attendee updated successfully")
                .payload(responseAttendees)
                .timestamp(Instant.now())
                .build();
    }

    @Override
    public ApiResponse<Void> deleteAttendee(Long attendeeId) {
        Attendees attendee = attendeeRepository.getAttendeeById(attendeeId);

        if (attendee == null) {
            throw new NotFoundException("No attendee found with the given ID: " + attendeeId);
        }

        int rowsDeleted = attendeeRepository.deleteAttendee(attendeeId);

        if (rowsDeleted <= 0) {
            throw new BadRequestException("Failed to delete attendee");
        }

        return ApiResponse.<Void>builder()
                .success(true)
                .status(HttpStatus.OK.value())
                .messages("Attendee deleted successfully")
                .timestamp(Instant.now())
                .build();
    }
}
