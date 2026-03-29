package com.hrd.springhomework03.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestEvent {
    private String eventName;
    private String eventDate;
    private Long venueId;
}
