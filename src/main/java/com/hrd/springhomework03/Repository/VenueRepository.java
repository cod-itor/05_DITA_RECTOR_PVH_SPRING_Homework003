package com.hrd.springhomework03.Repository;

import com.hrd.springhomework03.Model.Entity.Venue;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface VenueRepository {

    @Select("SELECT * FROM venues LIMIT #{size} OFFSET #{offset}")
    List<Venue> getAllVenue(Integer offset, Integer size);
}
