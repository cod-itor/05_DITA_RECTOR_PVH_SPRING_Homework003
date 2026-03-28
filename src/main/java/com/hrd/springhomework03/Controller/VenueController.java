package com.hrd.springhomework03.Controller;

import com.hrd.springhomework03.Model.Entity.Venue;
import com.hrd.springhomework03.Service.VenueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class VenueController {
     private final VenueService venueService;

    @GetMapping
    public ResponseEntity<List<Venue>> getAllVenue(@RequestParam Integer size , @RequestParam Integer page){
        return ResponseEntity.ok(venueService.getAllVenue(size , page));
    }




}
