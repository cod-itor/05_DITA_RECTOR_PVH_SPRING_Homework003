package com.hrd.springhomework03.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEvent {
    private Long eventId;
    private String eventName;
    private String eventDate;
    private Long venueId;
    private String venueName;
    private String location;
}
