package com.sg.m4herosightings.dao;

import com.sg.m4herosightings.dto.Hero;
import java.util.List;

public interface HeroDao {

    /**
     * Add a hero to db
     *
     * @param hero {Hero} a well formed hero obj
     * @return {Hero} a successfully added Hero obj
     */
    Hero createHero(Hero hero);

    Hero readHeroById(int id);

    List<Hero> readAllHeroes();

    Hero updateHero(Hero hero);

    boolean deleteHeroById(int id);

}
