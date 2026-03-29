package com.hrd.springhomework03.Controller;

import com.hrd.springhomework03.Model.Entity.Venue;
import com.hrd.springhomework03.Service.VenueService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/venue")
public class VenueController {
     private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }

    @Operation(summary = "hello")
    @GetMapping
    public ResponseEntity<List<Venue>> getAllVenue(@RequestParam (defaultValue = "1") Integer page , @RequestParam (defaultValue = "10") Integer size){
        List<Venue> listVenue = venueService.getAllVenue(page , size);
        return ResponseEntity.status(HttpStatus.OK).body(listVenue);
    }




}
