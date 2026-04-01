package com.hrd.springhomework03.Model.Response;

import com.hrd.springhomework03.Model.Entity.Venue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEvent {
    private Long eventId;
    private String eventName;
    private LocalDateTime eventDate;
    private Venue venueId;
}
