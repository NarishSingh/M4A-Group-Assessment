package com.sg.m4herosightings.dao;

import com.sg.m4herosightings.dto.Superpower;
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
public class SuperpowerDaoDb implements SuperpowerDao {

    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public List<Superpower> readAllSuperpowers() {
        final String GET_ALL_SUPERPOWER = "SELECT * FROM superpower";
        return jdbc.query(GET_ALL_SUPERPOWER, new SuperpowerMapper());
    }

    @Override
    @Transactional
    public Superpower createSuperpower(Superpower superpower) {
        final String ADD_SUPERPOWER = "INSERT INTO superpower(name, description) VALUES(?,?)";
        jdbc.update(ADD_SUPERPOWER, superpower.getName(), superpower.getDescription());
        int id = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        superpower.setSuperpowerId(id);
        return superpower;
    }

    @Override
    public Superpower readSuperpowerById(int id) {
        try{
            final String GET_SUPERPOWER = "SELECT * FROM superpower WHERE superpowerId = ?";
            return jdbc.queryForObject(GET_SUPERPOWER, new SuperpowerMapper(), id);
        }catch(DataAccessException ex){
            return null;
        }
    }

    @Override
    @Transactional
    public void updateSuperpower(Superpower superpower) {
        final String UPDATE_SUPERPOWER = "UPDATE superpower SET name=?, description = ? WHERE superpowerId = ?";
        jdbc.update(UPDATE_SUPERPOWER, superpower.getName(), superpower.getDescription(), superpower.getSuperpowerId());
    }

    @Override
    @Transactional
    public void deleteSuperpower(int id) {
        final String DELETE_SUPERPOWER_HERO = "DELETE FROM hero WHERE superpowerId = ?";
        jdbc.update(DELETE_SUPERPOWER_HERO, id);
        
        final String DELETE_SUPERPOWER = "DELETE FROM superpower WHERE superpowerId = ?";
        jdbc.update(DELETE_SUPERPOWER, id);
        
        /*
            before delete hero we may gonna need delete query for organization_hero bridge and sightings
            or we just need to call delete function from thoes dao methods. I'm not sure
        */
    }
    
    /*mapper*/
    public static final class SuperpowerMapper implements RowMapper<Superpower>{

        @Override
        public Superpower mapRow(ResultSet rs, int i) throws SQLException {
            Superpower superpower = new Superpower();
            superpower.setSuperpowerId(rs.getInt("superpowerId"));
            superpower.setName(rs.getString("name"));
            superpower.setDescription(rs.getString("description"));
            return superpower;
        }
        
    }
    
}