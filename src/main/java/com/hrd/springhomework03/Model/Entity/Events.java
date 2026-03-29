package com.hrd.springhomework03.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Events {
    private Long eventId;
    private String eventName;
    private String eventDate;
    private Venue venueId;
}
