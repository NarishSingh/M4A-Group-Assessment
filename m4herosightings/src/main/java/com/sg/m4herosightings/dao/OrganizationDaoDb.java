package com.sg.m4herosightings.dao;

import com.sg.m4herosightings.dto.Organization;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;


public class OrganizationDaoDb implements OrganizationDao {

    @Override
    public Organization dummyMethod() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /*Mapper*/
    public static final class OrganizationMapper implements RowMapper<Organization> {

        @Override
        public Organization mapRow(ResultSet rs, int index) throws SQLException {
            Organization o = new Organization();
            o.setOrganizationId(rs.getInt("organizationId"));
            o.setName(rs.getString("name"));
            o.setDescription(rs.getString("description"));
            o.setPhone(rs.getString("phone"));
            o.setEmail(rs.getString("email"));
            //Location and list of member heroes will be brought in by helper methods
            
            return o;
        }
    }
    
}
