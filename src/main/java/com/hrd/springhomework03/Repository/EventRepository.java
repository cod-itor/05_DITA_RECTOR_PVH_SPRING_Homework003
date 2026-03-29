package com.hrd.springhomework03.Repository;

import com.hrd.springhomework03.Model.Entity.Events;
import com.hrd.springhomework03.Model.Request.RequestEvent;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface EventRepository {

    @Select("SELECT * FROM events LIMIT #{size} OFFSET #{offset}")
    List<Events> getAllEvents(Integer offset, Integer size);

    @Select("SELECT * FROM events WHERE event_id = #{eventId}")
    Events getEventById(Long eventId);

    @Insert("INSERT INTO events (event_name, event_date, venue_id) " +
            "VALUES (#{req.eventName}, #{req.eventDate}, #{req.venueId}) " +
            "RETURNING event_id")
    Long createEvent(@Param("req") RequestEvent requestEvent);

    @Update("UPDATE events SET event_name = #{req.eventName}, " +
            "event_date = #{req.eventDate}, venue_id = #{req.venueId} " +
            "WHERE event_id = #{eventId}")
    int updateEvent(@Param("eventId") Long eventId, @Param("req") RequestEvent requestEvent);

    @Delete("DELETE FROM events WHERE event_id = #{eventId}")
    int deleteEvent(Long eventId);
}

