package com.sg.m4herosightings.dao;

import com.sg.m4herosightings.dao.SuperpowerDaoDb.SuperpowerMapper;
import com.sg.m4herosightings.dto.Hero;
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
public class HeroDaoDb implements HeroDao {
    
    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public List<Hero> readAllHeroes() {
        final String GET_ALL_HEROES = "SELECT * FROM hero";
        List<Hero> heroes =  jdbc.query(GET_ALL_HEROES, new HeroMapper());
        getPowersForHeroes(heroes);
        return heroes;
    }

    
    @Override
    @Transactional
    public Hero createHero(Hero hero) {
        final String ADD_HERO = "INSERT INTO hero(name, description, superpowerId) VALUES(?,?,?)";
        jdbc.update(ADD_HERO, hero.getName(), hero.getDescription(), hero.getSuperpower().getSuperpowerId());
        int id = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        hero.setHeroId(id);
        return hero;
    }

    @Override
    public Hero readHeroById(int id) {
        try{
            final String GET_HERO = "SELECT * FROM hero WHERE heroId = ?";
            Hero hero = jdbc.queryForObject(GET_HERO, new HeroMapper(), id);
            hero.setSuperpower(getSuperPowerForHero(id));
            return hero;
        }catch(DataAccessException ex){
            return null;
        }
    }

    @Override
    @Transactional
    public void updateHero(Hero hero) {
        final String UPDATE_HERO = "UPDATE hero SET name=?, description=?, superpowerId=? WHERE heroId = ?";
        jdbc.update(UPDATE_HERO, hero.getName(), hero.getDescription(), hero.getSuperpower().getSuperpowerId(), hero.getHeroId());
    }
    
    
    @Override
    @Transactional
    public void deleteHero(int id) {
        /*
            first delete from organization_heros bride table
            second delete from sightings table
            third delete from hero table
        */
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Superpower getSuperPowerForHero(int id) {
        final String GET_POWER_FOR_HERO = "SELECT sp.* FROM superpower sp JOIN hero h ON h.superpowerId = sp.superpowerId WHERE h.heroId = ?";
        return jdbc.queryForObject(GET_POWER_FOR_HERO, new SuperpowerMapper(), id);
    }

    private void getPowersForHeroes(List<Hero> heroes) {
        for(Hero hero: heroes){
            hero.setSuperpower(getSuperPowerForHero(hero.getHeroId()));
        }
    }
    
    /*mapper*/
    public static final class HeroMapper implements RowMapper<Hero>{

        @Override
        public Hero mapRow(ResultSet rs, int i) throws SQLException {
            Hero hero = new Hero();
            hero.setHeroId(rs.getInt("heroId"));
            hero.setName(rs.getString("name"));
            hero.setDescription(rs.getString("description"));
            return hero;
        }
        
    }
}