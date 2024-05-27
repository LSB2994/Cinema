package org.kshrd.leng_sambath_btb_homeword03.service.impliments;

import org.kshrd.leng_sambath_btb_homeword03.model.entity.Attendees;
import org.kshrd.leng_sambath_btb_homeword03.model.entity.Event;
import org.kshrd.leng_sambath_btb_homeword03.model.request.EventRequest;
import org.kshrd.leng_sambath_btb_homeword03.repository.EventRepository;
import org.kshrd.leng_sambath_btb_homeword03.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImp implements EventService {
    private final EventRepository eventRepository;

    public EventServiceImp(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> getAllEvents(Integer page, Integer size) {
        return eventRepository.getAllEvents(page,size);
    }

    @Override
    public Event getEventById(Integer eventId) {
        return eventRepository.getEventById(eventId);
    }

    @Override
    public boolean deleteEventById(Integer eventId) {
        return eventRepository.deleteEventById(eventId);
    }

    @Override
    public Integer postEvent(EventRequest eventRequest) {
        Integer eventId = eventRepository.saveEvent(eventRequest);
        if (eventId!=null){
            for (Integer attendId : eventRequest.getAttendees()){
                eventRepository.saveAttendEventId(eventId,attendId);;
            }
        }
        return eventId;
    }

    @Override
    public Integer updateEventById(EventRequest eventRequest, Integer eventId) {
        return eventRepository.updateEventById(eventRequest,eventId);
    }
}
