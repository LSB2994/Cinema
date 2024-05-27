package org.kshrd.leng_sambath_btb_homeword03.controller;

import org.kshrd.leng_sambath_btb_homeword03.exception.FieldEmptyException;
import org.kshrd.leng_sambath_btb_homeword03.exception.VenueNotFountException;
//import org.kshrd.leng_sambath_btb_homeword03.exception.attendeesException.AttendeesNotFountException;
import org.kshrd.leng_sambath_btb_homeword03.model.entity.Attendees;
import org.kshrd.leng_sambath_btb_homeword03.model.request.AttendeesRequest;
import org.kshrd.leng_sambath_btb_homeword03.model.response.AttendeesResposne;
import org.kshrd.leng_sambath_btb_homeword03.service.AttendeesSevice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/v1/attendees")
public class AttendeesController {
    private final AttendeesSevice attendeesSevice;

    public AttendeesController(AttendeesSevice attendeesSevice) {
        this.attendeesSevice = attendeesSevice;
    }

    //get all attendees
    @GetMapping()
    public ResponseEntity<AttendeesResposne<List<Attendees>>> getAllAttendees(@RequestParam(name = "page",defaultValue = "1") Integer page,
                                                                          @RequestParam(name = "size",defaultValue = "3") Integer size) {
        AttendeesResposne<List<Attendees>> response = AttendeesResposne.<List<Attendees>>builder()
                .message("All attendees have been successfully fetched.")
                .payload(attendeesSevice.getAllAttendees(page, size))
                .httpStatus(HttpStatus.OK)
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
        return ResponseEntity.ok(response);
    }

    //get attend by id
    @GetMapping("/{id}")
    public ResponseEntity<AttendeesResposne<Attendees>> getAttendeeById(@PathVariable("id") Integer attendeeId){
        AttendeesResposne<Attendees> response = null;
        if (attendeesSevice.getAttendeeById(attendeeId) !=null){
            response = AttendeesResposne.<Attendees>builder()
                    .message("The attend has been successfully founded.")
                    .payload(attendeesSevice.getAttendeeById(attendeeId))
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
        }else throw new VenueNotFountException("The attend id "+attendeeId+" is not found");
        return ResponseEntity.ok(response);
    }
    //post attend
    @PostMapping()
    public ResponseEntity<?> postAttendee(AttendeesRequest attendeesRequest){
        AttendeesResposne<Attendees> response = null;
        Integer postId = attendeesSevice.postAttendee(attendeesRequest);
        if (postId != null){
            response=AttendeesResposne.<Attendees>builder()
                    .message("The attend has been successfully added.")
                    .payload(attendeesSevice.getAttendeeById(postId))
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
//            return ResponseEntity.ok(response);
        }else throw new FieldEmptyException("The attend has been successfully added already.");
        return ResponseEntity.ok(response);
    }

    //update attend
    @PutMapping("/{id}")
    public ResponseEntity<AttendeesResposne<Attendees>> updateAttendee(@RequestBody AttendeesRequest attendeesRequest,
                                                            @PathVariable("id") Integer attendeeId
    )
    {
        Integer updateId = attendeesSevice.updateAttendeeById(attendeesRequest,attendeeId);
        AttendeesResposne<Attendees> response = null;
        if (updateId!=null){
            response = AttendeesResposne.<Attendees>builder()
                    .message("The attend has been successfully updated.")
                    .payload(attendeesSevice.getAttendeeById(updateId))
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
        }else throw new VenueNotFountException("The attend id "+attendeeId+" is not found");
        return ResponseEntity.ok(response);
    }


    //Delete attend by id
    @DeleteMapping("/{id}")
    public ResponseEntity<AttendeesResposne<Attendees>> deleteAttendee(@PathVariable("id") Integer attendeeId){
        AttendeesResposne<Attendees> response = null;
        if (attendeesSevice.deleteAttendeeById(attendeeId)){
            response = AttendeesResposne.<Attendees>builder()
                    .message("The attend has been successfully deleted.")
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
        }else throw new VenueNotFountException("The attend id "+attendeeId+" is not found");
        return ResponseEntity.ok(response);
    }
}
