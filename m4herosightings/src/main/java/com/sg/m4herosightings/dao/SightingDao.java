package com.sg.m4herosightings.dao;

import com.sg.m4herosightings.dto.Hero;
import com.sg.m4herosightings.dto.Location;
import com.sg.m4herosightings.dto.Sighting;
import java.time.LocalDate;
import java.util.List;

public interface SightingDao {

    /**
     * Create a new Sighting in db
     *
     * @param sighting {Sighting} a well formed Sighting obj
     * @return {Sighting} a successfully inserted Sighting Obj from table
     */
    Sighting createSighting(Sighting sighting);

    /**
     * Get an existing Sighting from db
     *
     * @param id {int} the id for an existing sighting
     * @return {Sighting} the obj from table, null if read fails
     */
    Sighting readSightingById(int id);

    /**
     * Update a Sighting in db
     *
     * @param updated {Sighting} the new Sighting obj
     * @return {Sighting} a successfully updated Sighting Obj from table
     */
    Sighting updateSighting(Sighting updated);

    /**
     * Delete a Sighting in db
     *
     * @param id {int} the id for an existing sighting
     * @return {Sighting} the obj from table
     */
    Sighting deleteSightingById(int id);

    /**
     * Get all Heroes/Villians sighted at a particular Location
     *
     * @param location {Location} a well formed Location obj
     * @return {List} a list of all Hero obj's for this location
     */
    List<Hero> readSightingsByLocation(Location location);

    /**
     * Get all sighting Locations for a particular Hero/Villians
     *
     * @param hero {Hero} a well formed Hero obj
     * @return {List} all Locations where this Hero was sighted
     */
    List<Location> readSightingByHero(Hero hero);

    /**
     * Get all sightings for a particular date
     *
     * @param date {LocalDate} a past date
     * @return {List} all sightings for this date
     */
    List<Sighting> readSightingsByDate(LocalDate date);
//    Map<Hero, Location> readSightingsByDate(LocalDate date);
//    Map<Hero, List<Location>> readSightingsByDate(LocalDate date); //if a hero can be sighted at multiple locations

}
