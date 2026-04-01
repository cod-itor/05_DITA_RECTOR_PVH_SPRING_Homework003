package com.hrd.springhomework03.Model.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAttendees {
    private Long attendeeId;
    private String attendeeName;
    private String email;
}
