package org.kshrd.leng_sambath_btb_homeword03.service;

import org.kshrd.leng_sambath_btb_homeword03.model.entity.Venue;
import org.kshrd.leng_sambath_btb_homeword03.model.request.VenueRequest;

import java.util.List;

public interface VenueService {
    List<Venue> getAllVenue(Integer page,Integer size);
    Venue getVenueById(Integer venueId);
    Integer postVenue(VenueRequest venueRequest);
    Integer updateVenueById(VenueRequest venueRequest,Integer venueId);
    boolean deleteVenueById(Integer venueId);
}
