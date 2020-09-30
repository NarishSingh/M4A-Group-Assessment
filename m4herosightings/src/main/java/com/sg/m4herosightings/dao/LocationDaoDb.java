package com.sg.m4herosightings.dao;

import com.sg.m4herosightings.dto.Location;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class LocationDaoDb implements LocationDao {

    @Override
    public Location dummyMethod() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

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
