package com.sg.m4herosightings.dao;

import com.sg.m4herosightings.dto.Location;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class LocationDaoDb implements LocationDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Location createLocation(Location location) {
        String insertQuery = "INSERT INTO location (latitude, longitude, name, description, street, city, state, zipcode) "
                + "VALUES(?,?,?,?,?,?,?,?);";
        jdbc.update(insertQuery,
                location.getLatitude(),
                location.getLongitude(),
                location.getName(),
                location.getDescription(),
                location.getStreet(),
                location.getCity(),
                location.getState(),
                location.getZipcode(),
                location.getLocationId());

        //get id
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        location.setLocationId(newId);

        return location;
    }

    @Override
    public Location readLocationById(int id) {
        try {
            String readQuery = "SELECT * FROM location "
                    + "WHERE locationId = ?;";
            return jdbc.queryForObject(readQuery, new LocationMapper(), id);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Location> readAllLocations() {
        String selectAllQuery = "SELECT * FROM location;";
        return jdbc.query(selectAllQuery, new LocationMapper());
    }

    @Override
    public Location updateLocation(Location update) {
        String updateQuery = "UPDATE location "
                + "SET"
                + "latitude = ?, "
                + "longitude = ?, "
                + "name = ?, "
                + "description = ?, "
                + "street = ?, "
                + "city = ?, "
                + "state = ?, "
                + "zipcode = ? "
                + "WHERE locationId = ?;";
        int success = jdbc.update(updateQuery,
                update.getLatitude(),
                update.getLongitude(),
                update.getName(),
                update.getDescription(),
                update.getStreet(),
                update.getCity(),
                update.getState(),
                update.getZipcode(),
                update.getLocationId());

        if (success == 1) {
            return update;
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public boolean deleteLocationById(int id) {
        //delete from organization
        String deleteOrgQuery = "DELETE * FROM organization "
                + "WHERE locationId = ?;";
        jdbc.update(deleteOrgQuery, id);

        //delete from sighting
        String deleteSightingQuery = "DELETE * FROM sighting "
                + "WHERE locationId = ?;";
        jdbc.update(deleteOrgQuery, id);

        //delete from location
        String deleteLocationQuery = "DELETE * from location "
                + "WHERE locationId = ?;";
        return jdbc.update(deleteOrgQuery, id) > 0;
    }

    /*Mapper*/
    public static final class LocationMapper implements RowMapper<Location> {

        @Override
        public Location mapRow(ResultSet rs, int index) throws SQLException {
            Location l = new Location();
            l.setLocationId(rs.getInt("locationId"));
            l.setLatitude(rs.getDouble("latitude"));
            l.setLongitude(rs.getDouble("longitude"));
            l.setName(rs.getString("name"));
            l.setDescription(rs.getString("description"));
            l.setStreet(rs.getString("street"));
            l.setCity(rs.getString("city"));
            l.setState(rs.getString("state"));
            l.setZipcode(rs.getString("zipcode"));

            return l;
        }

    }

}
