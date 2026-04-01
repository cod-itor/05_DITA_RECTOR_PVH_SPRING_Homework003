package com.hrd.springhomework03.Model.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestAttendees {
    private String attendeeName;
    private String email;
}
