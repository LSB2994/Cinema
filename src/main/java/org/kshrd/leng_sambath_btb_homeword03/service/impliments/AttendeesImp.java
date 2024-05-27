package org.kshrd.leng_sambath_btb_homeword03.service.impliments;

import org.kshrd.leng_sambath_btb_homeword03.model.entity.Attendees;
import org.kshrd.leng_sambath_btb_homeword03.model.request.AttendeesRequest;
import org.kshrd.leng_sambath_btb_homeword03.repository.AttendeesRepository;
import org.kshrd.leng_sambath_btb_homeword03.service.AttendeesSevice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendeesImp implements AttendeesSevice {
    private final AttendeesRepository attendeesRepository;

    public AttendeesImp(AttendeesRepository attendeesRepository) {
        this.attendeesRepository = attendeesRepository;
    }

    @Override
    public List<Attendees> getAllAttendees(Integer page, Integer size) {
        return attendeesRepository.getAllAttendees(page,size);
    }

    @Override
    public Attendees getAttendeeById(Integer attendId) {
        return attendeesRepository.getAttendeeById(attendId);
    }

    @Override
    public Integer postAttendee(AttendeesRequest attendeesRequest) {
        return attendeesRepository.postAttendee(attendeesRequest);
    }

    @Override
    public Integer updateAttendeeById(AttendeesRequest attendeesRequest, Integer attendeeId) {
        return attendeesRepository.updateAttendeeById(attendeesRequest,attendeeId);
    }

    @Override
    public boolean deleteAttendeeById(Integer attendeeId) {
        return attendeesRepository.deleteAttendeeById(attendeeId);
    }
}
