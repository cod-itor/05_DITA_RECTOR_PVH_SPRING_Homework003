package com.hrd.springhomework03.Service.impl;

import com.hrd.springhomework03.Model.Entity.Venue;
import com.hrd.springhomework03.Repository.VenueRepository;
import com.hrd.springhomework03.Service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;
    @Override
    public List<Venue>getAllVenue(Integer size , Integer page){

        Integer offset = (page - 1) * size  ;
        return venueRepository.getAllVenue(offset , page);
    }




}
