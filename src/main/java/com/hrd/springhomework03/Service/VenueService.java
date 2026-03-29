package com.hrd.springhomework03.Service;

import com.hrd.springhomework03.Model.Entity.Venue;
import com.hrd.springhomework03.Repository.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface VenueService {


    List<Venue> getAllVenue(Integer page , Integer size);
}
