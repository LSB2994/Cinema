package org.kshrd.leng_sambath_btb_homeword03.service.impliments;

import org.kshrd.leng_sambath_btb_homeword03.model.entity.Venue;
import org.kshrd.leng_sambath_btb_homeword03.model.request.VenueRequest;
import org.kshrd.leng_sambath_btb_homeword03.repository.VenueRepository;
import org.kshrd.leng_sambath_btb_homeword03.service.VenueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueImp implements VenueService {
    private final VenueRepository venueRepository;

    public VenueImp(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }

    @Override
    public List<Venue> getAllVenue(Integer page, Integer size) {
        return venueRepository.getAllVenues(page,size);
    }

    @Override
    public Venue getVenueById(Integer venueId) {
        return venueRepository.getVenueById(venueId);
    }

    @Override
    public Integer postVenue(VenueRequest venueRequest) {
        return venueRepository.insertVenue(venueRequest);
    }

    @Override
    public Integer updateVenueById(VenueRequest venueRequest, Integer venueId) {
        return venueRepository.updateVenueById(venueRequest,venueId);
    }

    @Override
    public boolean deleteVenueById(Integer venueId) {
        return venueRepository.deleteVenueById(venueId);
    }
}
