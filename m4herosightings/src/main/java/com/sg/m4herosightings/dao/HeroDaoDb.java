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
    @Transactional
    public Hero createHero(Hero hero) {
        final String ADD_HERO = "INSERT INTO hero(name, description, superpowerId, photoFileName) "
                + "VALUES(?,?,?,?);";
        jdbc.update(ADD_HERO, 
                hero.getName(), 
                hero.getDescription(),
                hero.getSuperpower().getSuperpowerId(),
                hero.getPhotoFileName());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        hero.setHeroId(newId);
        hero.setSuperpower(readSuperpowerForHero(newId));

        return hero;
    }

    @Override
    public Hero readHeroById(int id) {
        try {
            final String GET_HERO = "SELECT * FROM hero "
                    + "WHERE heroId = ?";
            Hero hero = jdbc.queryForObject(GET_HERO, new HeroMapper(), id);
            hero.setSuperpower(readSuperpowerForHero(id));
          
            return hero;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Hero> readAllHeroes() {
        final String GET_ALL_HEROES = "SELECT * FROM hero;";
        List<Hero> heroes = jdbc.query(GET_ALL_HEROES, new HeroMapper());
        associatePowersForHeroes(heroes);

        return heroes;
    }

    @Override
    @Transactional
    public Hero updateHero(Hero hero) {
        final String UPDATE_HERO = "UPDATE hero "
                + "SET "
                + "name = ?, "
                + "description = ?, "
                + "superpowerId = ?, "
                + "photoFileName = ? "
                + "WHERE heroId = ?;";
        int updated = jdbc.update(UPDATE_HERO,
                hero.getName(),
                hero.getDescription(),
                hero.getSuperpower().getSuperpowerId(),
                hero.getPhotoFileName(),
                hero.getHeroId());

        if (updated == 1) {
            return hero;
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public boolean deleteHeroById(int id) {
        //delete from bridge
        final String DELETE_HERO_HEROORGANIZATION = "DELETE FROM heroOrganization "
                + "WHERE heroId = ?;";
        jdbc.update(DELETE_HERO_HEROORGANIZATION, id);
        
        //delete from sightings
        final String DELETE_HERO_SIGHTING = "DELETE FROM sighting "
                + "WHERE heroId = ?;";
        jdbc.update(DELETE_HERO_SIGHTING, id);
        
        //delete hero
        final String DELETE_HERO = "DELETE FROM hero "
                + "WHERE heroId = ?;";
        return jdbc.update(DELETE_HERO, id) > 0;
    }

    /*Helpers*/
    /**
     * Query the superpower for a hero
     *
     * @param id {int} id for a superhero
     * @return {Superpower} the obj from fb
     */
    private Superpower readSuperpowerForHero(int id) {
        final String GET_POWER_FOR_HERO = "SELECT sp.* FROM superpower sp "
                + "JOIN hero h ON h.superpowerId = sp.superpowerId "
                + "WHERE h.heroId = ?;";
        return jdbc.queryForObject(GET_POWER_FOR_HERO, new SuperpowerMapper(), id);
    }

    /**
     * Set Superpower objs to their respective heroes
     *
     * @param heroes {List} heroes to be associated with superpowers
     */
    private void associatePowersForHeroes(List<Hero> heroes) {
        for (Hero hero : heroes) {
            hero.setSuperpower(readSuperpowerForHero(hero.getHeroId()));
        }
    }

    /*Mapper*/
    public static final class HeroMapper implements RowMapper<Hero> {

        @Override
        public Hero mapRow(ResultSet rs, int i) throws SQLException {
            Hero hero = new Hero();
            hero.setHeroId(rs.getInt("heroId"));
            hero.setName(rs.getString("name"));
            hero.setDescription(rs.getString("description"));
            hero.setPhotoFileName(rs.getString("photoFileName"));

            return hero;
        }

    }
}
