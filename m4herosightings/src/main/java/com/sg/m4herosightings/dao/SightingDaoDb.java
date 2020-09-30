package com.sg.m4herosightings.dao;

import com.sg.m4herosightings.dao.HeroDaoDb.HeroMapper;
import com.sg.m4herosightings.dao.LocationDaoDb.LocationMapper;
import com.sg.m4herosightings.dto.Hero;
import com.sg.m4herosightings.dto.Location;
import com.sg.m4herosightings.dto.Sighting;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
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
        String insertQuery = "INSERT INTO sighting(date, description, heroId, locationId) "
                + "VALUES(?,?,?,?);";
        jdbc.update(insertQuery,
                sighting.getDate(),
                sighting.getDescription(),
                sighting.getHero().getHeroId(),
                sighting.getLocation().getLocationId());

        //get id
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sighting.setSightingId(newId);

        return sighting;
    }

    @Override
    public Sighting readSightingById(int id) {
        try {
            String selectQuery = "SELECT * FROM sighting "
                    + "WHERE sightingId = ?;";
            Sighting s = jdbc.queryForObject(selectQuery, new SightingMapper(), id);
            s.setHero(readHeroForSighting(id));
            s.setLocation(readLocationForSighting(id));

            return s;
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public Sighting updateSighting(Sighting updated) {
        String updateQuery = "UPDATE sighting "
                + "SET "
                + "date = ?, "
                + "description = ?, "
                + "heroId = ?, "
                + "locationId = ?, "
                + "WHERE sightingId = ?;";
        int updateSuccess = jdbc.update(updateQuery, 
                updated.getDate(), 
                updated.getDescription(), 
                updated.getHero().getHeroId(), 
                updated.getLocation().getLocationId(),
                updated.getSightingId());
        
        if (updateSuccess == 1) {
            return updated;
        } else {
            return null;
        }
    }

    @Override
    public Sighting deleteSightingById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Hero> readSightingsByLocation(Location location) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Location> readSightingByHero(Hero hero) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Sighting> readSightingsByDate(LocalDate date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*Helpers*/
    /**
     * Query the Hero for a specific Sighting
     *
     * @param id {int} the sighting id
     * @return {Hero} the Hero from db
     */
    private Hero readHeroForSighting(int id) {
        String selectHeroQuery = "SELECT h.* FROM hero h "
                + "JOIN sighting s ON s.heroId = h.heroId "
                + "WHERE s.sightingId = ?;";
        return jdbc.queryForObject(selectHeroQuery, new HeroMapper(), id);
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
