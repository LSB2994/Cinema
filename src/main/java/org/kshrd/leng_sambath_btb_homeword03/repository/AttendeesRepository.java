package org.kshrd.leng_sambath_btb_homeword03.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.leng_sambath_btb_homeword03.model.entity.Attendees;
import org.kshrd.leng_sambath_btb_homeword03.model.request.AttendeesRequest;

import java.util.List;

@Mapper
public interface AttendeesRepository {
    //get all attendees
    @Select("""
    SELECT * FROM attendees LIMIT #{size} OFFSET ${size} * (#{page}-1)
""")
    @Results(
            id = "mapper",
            value = {
                    @Result(property = "attendeeId",column = "attendee_id"),
                    @Result(property = "attendeeName",column = "attendee_name"),
                    @Result(property = "email",column = "email")
            }
    )
    List<Attendees> getAllAttendees(Integer page,Integer size);

    //get attendee by id
    @Select("""
    SELECT * FROM attendees WHERE attendee_id = #{attendId}
""")
    @ResultMap("mapper")
    Attendees getAttendeeById(Integer attendId);

    //post attendee
    @Select("""
    INSERT INTO attendees(attendee_name, email) VALUES (#{rq.attendeeName},#{rq.email}) RETURNING attendee_id
""")
    Integer postAttendee(@Param("rq") AttendeesRequest attendeesRequest);

    //update attend by id
    @Select("""
            UPDATE attendees SET attendee_name = #{rq.attendeeName},email = #{rq.email} WHERE attendee_id = #{attendeeId} RETURNING attendee_id
""")
    Integer updateAttendeeById(@Param("rq") AttendeesRequest attendeesRequest,Integer attendeeId);

    //delete attend by is
    @Delete("DELETE  FROM attendees WHERE attendee_id = #{attendeeId}")
    @ResultMap("mapper")
    boolean deleteAttendeeById(Integer attendeeId);
}
