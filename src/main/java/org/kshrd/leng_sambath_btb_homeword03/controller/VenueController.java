package org.kshrd.leng_sambath_btb_homeword03.controller;

import org.kshrd.leng_sambath_btb_homeword03.exception.VenueNotFountException;
import org.kshrd.leng_sambath_btb_homeword03.model.entity.Venue;
import org.kshrd.leng_sambath_btb_homeword03.model.request.VenueRequest;
import org.kshrd.leng_sambath_btb_homeword03.model.response.VenueResponse;
import org.kshrd.leng_sambath_btb_homeword03.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/v1/venue")
public class VenueController {

    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }
    //get all venue
    @GetMapping()
    public ResponseEntity<VenueResponse<List<Venue>>> getAllVenue(@RequestParam(name = "page",defaultValue = "1") Integer page,
                                                                  @RequestParam(name = "size",defaultValue = "3") Integer size) {
        VenueResponse<List<Venue>> response = VenueResponse.<List<Venue>>builder()
                .message("All venues have been successfully fetched.")
                .payload(venueService.getAllVenue(page, size))
                .httpStatus(HttpStatus.OK)
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();
        return ResponseEntity.ok(response);
    }

    //get venue by id
    @GetMapping("/{id}")
    public ResponseEntity<VenueResponse<Venue>> getVenueById(@PathVariable("id") Integer venueId){
        VenueResponse<Venue> response = null;
        if (venueService.getVenueById(venueId) !=null){
            response = VenueResponse.<Venue>builder()
                    .message("The venue has been successfully founded.")
                    .payload(venueService.getVenueById(venueId))
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
        }else throw new VenueNotFountException("The venue id "+venueId+" is not found.");
        return ResponseEntity.ok(response);
    }
    //post venue
    @PostMapping()
    public ResponseEntity<?> postVenue(VenueRequest venueRequest){
        VenueResponse<Venue> response = null;
        Integer venueId = venueService.postVenue(venueRequest);
        if (venueId != null){
            response=VenueResponse.<Venue>builder()
                    .message("The venue has been successfully added.")
                    .payload(venueService.getVenueById(venueId))
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
//            return ResponseEntity.ok(response);
        }else{
            response = VenueResponse.<Venue>builder()
                    .message("post venue is not successfully")
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.ok(response);
    }

    //update venue
    @PutMapping("/{id}")
    public ResponseEntity<VenueResponse<Venue>> updateVenue(@RequestBody VenueRequest venueRequest,
                                                            @PathVariable("id") Integer venueId
                                                            )
    {
        Integer updateId = venueService.updateVenueById(venueRequest,venueId);
        VenueResponse<Venue> response = null;
        if (updateId!=null){
            response = VenueResponse.<Venue>builder()
                    .message("The venue has been successfully updated.")
                    .payload(venueService.getVenueById(updateId))
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
        }else throw new VenueNotFountException("The venue id "+venueId+" is not found.");
        return ResponseEntity.ok(response);
    }


    //Delete venue by id
    @DeleteMapping("/{id}")
    public ResponseEntity<VenueResponse<Venue>> deleteVenue(@PathVariable("id") Integer venueId){
        VenueResponse<Venue> response = null;
        if (venueService.deleteVenueById(venueId)){
            response = VenueResponse.<Venue>builder()
                    .message("The venue has been successfully deleted.")
                    .httpStatus(HttpStatus.OK)
                    .timestamp(new Timestamp(System.currentTimeMillis()))
                    .build();
        }else throw new VenueNotFountException("The venue id "+venueId+" is not found.");
        return ResponseEntity.ok(response);
    }
}
