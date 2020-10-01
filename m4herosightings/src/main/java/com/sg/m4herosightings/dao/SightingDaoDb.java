package com.sg.m4herosightings.dao;

import com.sg.m4herosightings.dao.HeroDaoDb.HeroMapper;
import com.sg.m4herosightings.dao.LocationDaoDb.LocationMapper;
import com.sg.m4herosightings.dao.SuperpowerDaoDb.SuperpowerMapper;
import com.sg.m4herosightings.dto.Hero;
import com.sg.m4herosightings.dto.Location;
import com.sg.m4herosightings.dto.Sighting;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class SightingDaoDb implements SightingDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Sighting createSighting(Sighting sighting) {
        //insert
        String insertQuery = "INSERT INTO sighting (date, description, heroId, locationId) "
                + "VALUES(?,?,?,?);";
        jdbc.update(insertQuery,
                sighting.getDate(),
                sighting.getDescription(),
                sighting.getHero().getHeroId(),
                sighting.getLocation().getLocationId());

        //get id
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sighting.setSightingId(newId);
        sighting.setHero(readHeroForSighting(sighting.getSightingId()));
        sighting.setLocation(readLocationForSighting(sighting.getSightingId()));

        return sighting;
    }

    @Override
    public Sighting readSightingById(int id) {
        try {
            //query sighting
            String selectQuery = "SELECT * FROM sighting "
                    + "WHERE sightingId = ?;";
            Sighting s = jdbc.queryForObject(selectQuery, new SightingMapper(), id);

            //associate hero and location obj's
            s.setHero(readHeroForSighting(id));
            s.setLocation(readLocationForSighting(id));

            return s;
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Sighting> readAllSightings() {
        String selectAllQuery = "SELECT * FROM sighting;";
        List<Sighting> sightings = jdbc.query(selectAllQuery, new SightingMapper());
        associateHeroesLocationsWithSightings(sightings);

        return sightings;
    }

    @Override
    public Sighting updateSighting(Sighting updated) {
        String updateQuery = "UPDATE sighting "
                + "SET "
                + "date = ?, "
                + "description = ?, "
                + "heroId = ?, "
                + "locationId = ? "
                + "WHERE sightingId = ?;";
        int updateSuccess = jdbc.update(updateQuery,
                updated.getDate(),
                updated.getDescription(),
                updated.getHero().getHeroId(),
                updated.getLocation().getLocationId(),
                updated.getSightingId());

        if (updateSuccess == 1) {
            return updated; //1 row effected = success
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteSightingById(int id) {
        String deleteQuery = "DELETE FROM sighting "
                + "WHERE sightingId = ?;";

        return jdbc.update(deleteQuery, id) > 0;
    }

    @Override
    public List<Hero> readHeroSightingsByLocation(Location location) {
        String heroSightingsQuery = "SELECT * FROM hero h "
                + "JOIN sighting s ON s.heroId = h.heroId "
                + "JOIN location l ON l.locationId = s.locationId "
                + "WHERE l.locationId = ?;";
        List<Hero> heroes = jdbc.query(heroSightingsQuery, new HeroMapper(), location.getLocationId());
        
        for (Hero h : heroes) {
            associateSuperpowerWithHero(h);
        }
        
        return heroes;
    }

    @Override
    public List<Location> readLocationSightingsByHero(Hero hero) {
        String locationSightingsQuery = "SELECT * FROM location l "
                + "JOIN sighting s ON s.locationId = l.locationId "
                + "JOIN hero h ON h.heroId = s.heroId "
                + "WHERE h.heroId = ?;";
        return jdbc.query(locationSightingsQuery, new LocationMapper(), hero.getHeroId());
    }

    @Override
    public List<Sighting> readSightingsByDate(LocalDate date) {
        String sightingsOnDateQuery = "SELECT * FROM sighting s "
                + "WHERE s.date = ?;";
        List<Sighting> allSightings = jdbc.query(sightingsOnDateQuery, new SightingMapper(), date);
        associateHeroesLocationsWithSightings(allSightings);
        
        return allSightings;
    }

    /*Helpers*/
    /**
     * Query the Hero for a specific Sighting
     *
     * @param id {int} the sighting id
     * @return {Hero} the Hero from db
     */
    private Hero readHeroForSighting(int id) {
        //read hero
        String selectHeroQuery = "SELECT h.* FROM hero h "
                + "JOIN sighting s ON s.heroId = h.heroId "
                + "WHERE s.sightingId = ?;";
        Hero h = jdbc.queryForObject(selectHeroQuery, new HeroMapper(), id);
        
        associateSuperpowerWithHero(h);
        
        return h;
    }

    /**
     * Set the Superpower field of a Hero obj from db
     * @param h {Hero} a Hero obj from db
     */
    private void associateSuperpowerWithHero(Hero h) {
        //associate superpower
        String selectSuperpowerQuery = "SELECT s.* FROM superpower s "
                + "JOIN hero h ON h.superpowerId = s.superpowerId "
                + "WHERE h.heroId = ?;";
        h.setSuperpower(jdbc.queryForObject(selectSuperpowerQuery, new SuperpowerMapper(), h.getHeroId()));
    }

    /**
     * Query the Location for a specific Sighting
     *
     * @param id {int} the sighting id
     * @return {Location} the Location from db
     */
    private Location readLocationForSighting(int id) {
        String selectLocQuery = "SELECT l.* FROM location l "
                + "JOIN sighting s ON s.locationId = l.locationId "
                + "WHERE s.sightingId = ?;";
        return jdbc.queryForObject(selectLocQuery, new LocationMapper(), id);
    }

    /**
     * Associate Heroes and Locations with their respective Sightings in memory
     *
     * @param sightings {List} sighting obj's to be associated
     */
    private void associateHeroesLocationsWithSightings(List<Sighting> sightings) {
        for (Sighting s : sightings) {
            s.setHero(readHeroForSighting(s.getSightingId()));
            s.setLocation(readLocationForSighting(s.getSightingId()));
        }
    }

    /*Mapper*/
    public static final class SightingMapper implements RowMapper<Sighting> {

        @Override
        public Sighting mapRow(ResultSet rs, int index) throws SQLException {
            Sighting s = new Sighting();
            s.setSightingId(rs.getInt("sightingId"));
            s.setDate(rs.getDate("date").toLocalDate());
            s.setDescription(rs.getString("description"));
            //Hero and Location obj's will be set by helpers

            return s;
        }

    }

}
