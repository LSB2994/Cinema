package org.kshrd.leng_sambath_btb_homeword03.repository;

import org.apache.ibatis.annotations.*;
import org.kshrd.leng_sambath_btb_homeword03.model.entity.Venue;
import org.kshrd.leng_sambath_btb_homeword03.model.request.VenueRequest;

import java.util.List;
@Mapper
public interface VenueRepository {

        //get all students
        @Select("""
            SELECT * FROM venues LIMIT #{size} OFFSET ${size} * (#{page}-1)
    """)
        @Results(
                id = "mapper",
                value = {
                        @Result(property = "venue_id",column = "venue_id"),
                        @Result(property = "venue_name",column = "venue_name"),
                        @Result(property = "location",column = "location")
                }
        )
        List<Venue> getAllVenues(Integer page, Integer size);

    //get venue by id
    @Select("""
            SELECT *FROM venues where venue_id = #{venueId}
    """)
        @ResultMap("mapper")
        Venue getVenueById(Integer venueId);

    //post venue
    @Select("""
    INSERT INTO venues(venue_name, location) VALUES (#{rq.venue_name},#{rq.location}) RETURNING venue_id
""")
    Integer insertVenue(@Param("rq") VenueRequest venueRequest);


    //update venue by id
    @Select("""
            UPDATE venues SET venue_name = #{rq.venue_name},location = #{rq.location} WHERE venue_id = #{venueId} RETURNING venue_id
""")
    Integer updateVenueById(@Param("rq") VenueRequest venueRequest,Integer venueId);

    // delete venue by id
    @Delete("DELETE  FROM venues WHERE venue_id = #{venueId}")
    @ResultMap("mapper")
    boolean deleteVenueById(Integer venueId);


}
