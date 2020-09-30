package com.sg.m4herosightings.dao;

import com.sg.m4herosightings.dto.Hero;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class HeroDaoDb implements HeroDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Hero dummyMethod() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static final class HeroMapper implements RowMapper<Hero> {

        @Override
        public Hero mapRow(ResultSet rs, int index) throws SQLException {
            Hero h = new Hero();
            h.setHeroId(rs.getInt("heroId"));
            h.setName(rs.getString("name"));
            h.setDescription(rs.getString("description"));
            //Superpower will be set by helpers
            
            return h;
        }
    }
    
}
