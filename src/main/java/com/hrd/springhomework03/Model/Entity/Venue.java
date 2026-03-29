package com.hrd.springhomework03.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Venue {
    private Long venueId;
    private String venueName;
    private String location;
}
