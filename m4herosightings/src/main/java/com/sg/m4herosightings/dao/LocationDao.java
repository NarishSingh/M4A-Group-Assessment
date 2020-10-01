package com.sg.m4herosightings.dao;

import com.sg.m4herosightings.dto.Location;
import java.util.List;

public interface LocationDao {

    /**
     * Create a new Location in db
     *
     * @param location {Location} a well formed Location obj
     * @return {Location} successfully added obj from db
     */
    Location createLocation(Location location);

    /**
     * Read a Location from db
     *
     * @param id {int} the id for an existing Location
     * @return {Location} the Location obj from db
     */
    Location readLocationById(int id);

    /**
     * Read all Locations from db
     *
     * @return {List} all objs
     */
    List<Location> readAllLocations();

    /**
     * Update a Location row in db
     *
     * @param update {Location} the update obj with corresponding id
     * @return {Location} the successfully updated obj from db, null otherwise
     */
    Location updateLocation(Location update);

    /**
     * Delete a Location from db
     *
     * @param id {int} the id for an existing Location
     * @return {boolean} true is deleted, false otherwise
     */
    boolean deleteLocationById(int id);

}