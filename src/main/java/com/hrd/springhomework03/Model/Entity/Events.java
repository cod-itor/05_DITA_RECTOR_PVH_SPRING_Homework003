package com.hrd.springhomework03.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Events {
    private Long eventId;
    private String eventName;
    private LocalDateTime eventDate;
    private Venue venueId;
}

