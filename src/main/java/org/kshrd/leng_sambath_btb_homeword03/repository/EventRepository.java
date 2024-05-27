package org.kshrd.leng_sambath_btb_homeword03.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.leng_sambath_btb_homeword03.model.entity.Event;
import org.kshrd.leng_sambath_btb_homeword03.model.request.EventRequest;

import java.util.List;

@Mapper
public interface EventRepository {
    //get students
    @Select("SELECT * FROM events "+
            "LIMIT #{size} "+
            "OFFSET ${size} * (#{page}-1)"
    )
    @Results(
            id = "mapper",
            value = {
                    @Result(property = "eventId",column = "event_id"),
                    @Result(property = "eventName",column = "event_name"),
                    @Result(property = "eventDate",column = "event_date"),
                    @Result(property = "venue",column = "eventId",
                    one = @One(select = "org.kshrd.leng_sambath_btb_homeword03.repository.VenueRepository.getVenueById")
                    ),
                    @Result(property = "attendees",column = "eventId",
                    many = @Many(select = "org.kshrd.leng_sambath_btb_homeword03.repository.AttendeesRepository.getAttendeeById")
                    )
            }
    )
    List<Event> getAllEvents(Integer page, Integer size);

    //get event by id
    @Select("SELECT * FROM events WHERE events_id = #{eventId}")
    @ResultMap("mapper")
    Event getEventById(Integer eventId);

    @Select("INSERT INTO events(event_name, event_date, venue_id) "+
            "VALUES (#{rq.eventName},#{rq.eventDate},#{rq.venueId}) "+
            "RETURNING events_id"
    )
    Integer saveEvent(@Param("rq") EventRequest eventRequest);

    @Select("INSERT INTO event_attendee(event_id, attendee_id) "+
            "VALUES (#{eventId},#{attendId})"
    )
    Integer saveAttendEventId(Integer eventId, Integer attendId);

    //update student
    @Select("UPDATE events" +
            " SET event_name = #{rq.eventName}, event_date = #{rq.eventDate}, venue_id = #{rq.venueId} WHERE events_id = #{eventId} "+
            "RETURNING events_id"
    )
    Integer updateEventById(@Param("rq") EventRequest eventRequest,Integer eventId);

    //delete student
    @Delete("DELETE FROM events WHERE events_id = #{eventId}")
    @ResultMap("mapper")
    boolean deleteEventById(Integer eventId);
}

