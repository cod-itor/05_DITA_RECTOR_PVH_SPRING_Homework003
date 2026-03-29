package com.hrd.springhomework03.Repository;

import com.hrd.springhomework03.Model.Entity.Venue;
import com.hrd.springhomework03.Model.Request.RequestVenue;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface VenueRepository {
    @Results(id="VenueMapper" , value = {
            @Result(property = "venueId" , column = "venue_id"),
            @Result(property = "venueName", column = "venue_name")

    })

    @Select("SELECT * FROM venues LIMIT #{size} OFFSET #{offset}")
    List<Venue> getAllVenue(Integer offset, Integer size);
    @ResultMap("VenueMapper")
    @Select("SELECT * FROM venues WHERE venue_id = #{venueId}")
    Venue getVenueById(Long venueId);
    @ResultMap("VenueMapper")
    @Select("INSERT INTO venues (venue_name, location) VALUES (#{req.venueName}, #{req.location}) RETURNING *")
    Venue createVenue(@Param("req") RequestVenue requestVenue);

    @ResultMap("VenueMapper")
    @Select("UPDATE venues SET venue_name = #{req.venueName}, location = #{req.location} WHERE venue_id = #{venueId} RETURNING *")
    Venue updateVenue(@Param("venueId") Long venueId, @Param("req") RequestVenue requestVenue);
    @ResultMap("VenueMapper")
    @Delete("DELETE FROM venues WHERE venue_id = #{venueId}")
    int deleteVenue(Long venueId);
}
