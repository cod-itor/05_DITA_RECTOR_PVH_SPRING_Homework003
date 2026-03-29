package com.hrd.springhomework03.Repository;

import com.hrd.springhomework03.Model.Entity.Events;
import com.hrd.springhomework03.Model.Request.RequestEvent;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EventRepository {
    @Results(id="EventMapper" , value = {
            @Result(property = "eventId" , column = "event_id"),
            @Result(property = "eventName", column = "event_name"),
            @Result(property = "eventDate", column = "event_date"),
            @Result(property = "venueId", column = "event_id", one = @One(select = "com.hrd.springhomework03.Repository.VenueRepository.getVenueById"))
    })


    @Select("SELECT * FROM events LIMIT #{size} OFFSET #{offset}")
    List<Events> getAllEvents(Integer offset, Integer size);

    @Select("SELECT * FROM events WHERE event_id = #{eventId}")
    Events getEventById(Long eventId);

    @Select("INSERT INTO events (event_name, event_date, venue_id) " +
            "VALUES (#{req.eventName}, #{req.eventDate}, #{req.venueId}) " +
            "RETURNING *")
    Events createEvent(@Param("req") RequestEvent requestEvent);

    @Select("UPDATE events SET event_name = #{req.eventName}, " +
            "event_date = #{req.eventDate}, venue_id = #{req.venueId} " +
            "WHERE event_id = #{eventId} " +
            "RETURNING *")
    Events updateEvent(@Param("eventId") Long eventId, @Param("req") RequestEvent requestEvent);

    @Delete("DELETE FROM events WHERE event_id = #{eventId}")
    int deleteEvent(Long eventId);
}

