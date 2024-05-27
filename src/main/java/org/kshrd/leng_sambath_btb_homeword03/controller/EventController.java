package org.kshrd.leng_sambath_btb_homeword03.controller;

import org.kshrd.leng_sambath_btb_homeword03.exception.VenueNotFountException;
import org.kshrd.leng_sambath_btb_homeword03.model.entity.Event;
import org.kshrd.leng_sambath_btb_homeword03.model.request.EventRequest;
import org.kshrd.leng_sambath_btb_homeword03.model.response.EventResponse;
import org.kshrd.leng_sambath_btb_homeword03.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    //get all events
    @GetMapping("all")
    public ResponseEntity<EventResponse<List<Event>>> getAllEvents(@RequestParam(name = "page",defaultValue = "1") Integer page,
                                                                    @RequestParam(name = "size",defaultValue = "3") Integer size){
        EventResponse<List<Event>> response = EventResponse.<List<Event>>builder()
                .message("get book is successfully")
                .payload(eventService.getAllEvents(page,size))
                .httpStatus(HttpStatus.OK)
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
        return ResponseEntity.ok(response);
    }

    //get event by id
    @GetMapping("{id}")
    public ResponseEntity<EventResponse<Event>> getEventById(@PathVariable("id") Integer eventId){
        EventResponse<Event> response = null;
        if (eventService.getEventById(eventId) != null){
            response = EventResponse.<Event>builder()
                    .message("get course by id "+eventId+" is successfully")
                    .payload(eventService.getEventById(eventId))
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
        }else throw new VenueNotFountException("The attend id "+eventId+" is not found.");
        return ResponseEntity.ok(response);
    }

    //Delete object event by id
    @DeleteMapping("/{id}")
    public ResponseEntity<EventResponse<Event>> deleteEventById(@PathVariable("id") Integer eventId){
        EventResponse<Event> response =null;
        if (eventService.deleteEventById(eventId)){
            response = EventResponse.<Event>builder()
                    .message("Delete event by "+eventId+" id successfully")
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
        }else throw new VenueNotFountException("The event id "+eventId+" is not found.");
        return ResponseEntity.ok(response);
    }

    //post evnet
    @PostMapping()
    public ResponseEntity<EventResponse<Event>> postEvent(@RequestBody EventRequest eventRequest){
        EventResponse<Event> response = null;
        Integer insertId = eventService.postEvent(eventRequest);
        if(insertId !=null){
            response = EventResponse.<Event>builder()
                    .message("post event is successfully")
                    .payload(eventService.getEventById(insertId))
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
        }else {
            response = EventResponse.<Event>builder()
                    .message("post event is not successfully")
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.ok(response);
    }

    // update course
    @PutMapping("/{id}")
    public ResponseEntity<EventResponse<Event>> updateEvent(@RequestBody EventRequest eventRequest,
                                                                @PathVariable("id") Integer eventId){
        Integer updateId = eventService.updateEventById(eventRequest, eventId);
        EventResponse<Event> response = null;
        if (updateId!=null){
            response = EventResponse.<Event>builder()
                    .message("Update event by id "+eventId+" is successfully")
                    .payload(eventService.getEventById(updateId))
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
        }else throw new VenueNotFountException("The event by id "+eventId+" is not fount");
        return ResponseEntity.ok(response);
    }
}
