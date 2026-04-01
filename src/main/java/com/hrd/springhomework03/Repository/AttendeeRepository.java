package com.hrd.springhomework03.Repository;

import com.hrd.springhomework03.Model.Entity.Attendees;
import com.hrd.springhomework03.Model.Request.RequestAttendees;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AttendeeRepository {

    @Results(id="AttendeeMapper", value = {
            @Result(property = "attendeeId", column = "attendee_id"),
            @Result(property = "attendeeName", column = "attendee_name"),
            @Result(property = "email", column = "email")
    })
    @Select("SELECT * FROM attendees LIMIT #{size} OFFSET #{offset}")
    List<Attendees> getAllAttendees(Integer offset, Integer size);

    @ResultMap("AttendeeMapper")
    @Select("SELECT * FROM attendees WHERE attendee_id = #{attendeeId}")
    Attendees getAttendeeById(Long attendeeId);

    @ResultMap("AttendeeMapper")
    @Select("INSERT INTO attendees (attendee_name, email) VALUES (#{req.attendeeName}, #{req.email}) RETURNING *")
    Attendees createAttendee(@Param("req") RequestAttendees requestAttendees);

    @ResultMap("AttendeeMapper")
    @Select("UPDATE attendees SET attendee_name = #{req.attendeeName}, email = #{req.email} WHERE attendee_id = #{attendeeId} RETURNING *")
    Attendees updateAttendee(@Param("attendeeId") Long attendeeId, @Param("req") RequestAttendees requestAttendees);

    @Delete("DELETE FROM attendees WHERE attendee_id = #{attendeeId}")
    int deleteAttendee(Long attendeeId);

    @Select("SELECT COUNT(*) FROM attendees WHERE attendee_id = #{attendeeId}")
    boolean attendeeExists(Long attendeeId);
}
