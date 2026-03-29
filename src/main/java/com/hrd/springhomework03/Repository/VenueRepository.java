package com.hrd.springhomework03.Repository;

import com.hrd.springhomework03.Model.Entity.Venue;
import com.hrd.springhomework03.Model.Request.RequestVenue;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VenueRepository {

    @Select("SELECT * FROM venues LIMIT #{size} OFFSET #{offset}")
    List<Venue> getAllVenue(Integer offset, Integer size);

    @Select("SELECT * FROM venues WHERE venue_id = #{venueId}")
    Venue getVenueById(Long venueId);

    @Select("INSERT INTO venues (venue_name, location) " +
            "VALUES (#{req.venueName}, #{req.location}) " +
            "RETURNING *")
    Venue createVenue(@Param("req") RequestVenue requestVenue);

    @Select("UPDATE venues SET venue_name = #{req.venueName}, " +
            "location = #{req.location} " +
            "WHERE venue_id = #{venueId} " +
            "RETURNING *")
    Venue updateVenue(@Param("venueId") Long venueId, @Param("req") RequestVenue requestVenue);

    @Delete("DELETE FROM venues WHERE venue_id = #{venueId}")
    int deleteVenue(Long venueId);
}
