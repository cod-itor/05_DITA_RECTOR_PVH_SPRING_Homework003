package com.hrd.springhomework03.Model.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventAttendee {
    private Events eventId;
    private Attendees attendeeId;
}
