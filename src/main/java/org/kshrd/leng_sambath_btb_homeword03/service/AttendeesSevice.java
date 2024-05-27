package org.kshrd.leng_sambath_btb_homeword03.service;

import org.kshrd.leng_sambath_btb_homeword03.model.entity.Attendees;
import org.kshrd.leng_sambath_btb_homeword03.model.request.AttendeesRequest;

import java.util.List;

public interface AttendeesSevice {
    List<Attendees> getAllAttendees(Integer page, Integer size);
    Attendees getAttendeeById(Integer attendId);
    Integer postAttendee(AttendeesRequest attendeesRequest);
    Integer updateAttendeeById(AttendeesRequest attendeesRequest,Integer attendeeId);
    boolean deleteAttendeeById(Integer attendeeId);
}
