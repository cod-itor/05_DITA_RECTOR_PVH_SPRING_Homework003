package com.hrd.springhomework03.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseVenue {
    private Long venueId;
    private String venueName;
    private String location;
}
