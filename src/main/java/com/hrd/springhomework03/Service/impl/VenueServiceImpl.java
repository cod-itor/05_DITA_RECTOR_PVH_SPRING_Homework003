package com.hrd.springhomework03.Service.impl;

import com.hrd.springhomework03.Model.Entity.Venue;
import com.hrd.springhomework03.Repository.VenueRepository;
import com.hrd.springhomework03.Service.VenueService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;

    public VenueServiceImpl(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }


    @Override
    public List<Venue>getAllVenue(Integer page , Integer size){

        Integer offset = (page - 1) * size  ;
        return venueRepository.getAllVenue(offset , size);
    }




}
