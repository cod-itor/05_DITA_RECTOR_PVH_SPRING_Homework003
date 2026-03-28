package com.hrd.springhomework03.Repository;

import com.hrd.springhomework03.Model.Entity.Venue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

import java.util.List;

@Mapper
public interface VenueRepository {

    @Results(id= "VenueMapper" , value = {
            @Result(property = "", column = "")
    })
    List<Venue> getAllVenue(Integer offset, Integer page);
}
