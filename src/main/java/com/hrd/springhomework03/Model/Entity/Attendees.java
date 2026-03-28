package com.hrd.springhomework03.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attendees {
    private Long attendeeId;
    private String attendeeName;
    private String email;


}
