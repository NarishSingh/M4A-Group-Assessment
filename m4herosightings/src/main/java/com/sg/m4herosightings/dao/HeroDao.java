package com.sg.m4herosightings.dao;

import com.sg.m4herosightings.dto.Hero;
import java.util.List;

public interface HeroDao {

    /**
     * Add a Hero to db
     *
     * @param hero {Hero} a well formed hero obj
     * @return {Hero} a successfully added Hero obj
     */
    Hero createHero(Hero hero);

    /**
     * Read a Hero from db
     * @param id {int} a valid id for an existing Hero
     * @return {Hero} the obj from db row
     */
    Hero readHeroById(int id);

    /**
     * Read all Heroes from db
     * @return {List} all objs from table
     */
    List<Hero> readAllHeroes();

    /**
     * Update a Hero row in db
     * @param hero {Hero} the update obj with corresponding id
     * @return {Hero} the successfully updated obj from db, null otherwise
     */
    Hero updateHero(Hero hero);

    /**
     * Delete a Hero from db
     * @param id {int} a valid id for an existing Hero
     * @return {boolean} true if successfully deleted, false otherwise
     */
    boolean deleteHeroById(int id);

}
