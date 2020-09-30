package com.sg.m4herosightings.dao;

import com.sg.m4herosightings.dto.Superpower;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;


public class SuperpowerDaoDb implements SuperpowerDao {

    @Override
    public Superpower dummyMethod() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    /*Mapper*/
    public static final class SuperpowerMapper implements RowMapper<Superpower> {

        @Override
        public Superpower mapRow(ResultSet rs, int index) throws SQLException {
            Superpower s = new Superpower();
            s.setSuperpowerId(rs.getInt("superpowerId"));
            s.setName(rs.getString("name"));
            s.setDescription(rs.getString("description"));
            
            return s;
        }
    }
    
}
