package com.hrd.springhomework03.Repository;

import com.hrd.springhomework03.Model.Entity.Events;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Optional;

@Mapper
public interface EventRepository {

    @Select("SELECT * FROM events LIMIT #{size} OFFSET #{offset}")
    List<Events> getAllEvents(Integer offset, Integer size);

    @Select("SELECT * FROM events WHERE event_id = #{eventId}")
    Optional<Events> getEventById(Long eventId);

    @Insert("INSERT INTO events (event_name, event_date, venue_id) VALUES (#{eventName}, #{eventDate}, #{venueId})")
    void createEvent(Events event);

    @Update("UPDATE events SET event_name = #{eventName}, event_date = #{eventDate}, venue_id = #{venueId} WHERE event_id = #{eventId}")
    int updateEvent(Events event);

    @Delete("DELETE FROM events WHERE event_id = #{eventId}")
    int deleteEvent(Long eventId);
}
