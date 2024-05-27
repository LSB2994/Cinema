package org.kshrd.leng_sambath_btb_homeword03.service;

import org.kshrd.leng_sambath_btb_homeword03.model.entity.Event;
import org.kshrd.leng_sambath_btb_homeword03.model.request.EventRequest;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents(Integer page, Integer size);
    Event getEventById(Integer eventId);
    boolean deleteEventById(Integer eventId);
    Integer postEvent(EventRequest eventRequest);
    Integer updateEventById(EventRequest eventRequest, Integer eventId);
}
