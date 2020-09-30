package com.sg.m4herosightings.dao;

import com.sg.m4herosightings.dto.Hero;
import java.util.List;

public interface HeroDao {
  
    List<Hero> getAllHeroes();
  
    Hero addHero(Hero hero);
  
    Hero getHeroById(int id);
  
    void updateHero(Hero hero);
  
    void deleteHero(int id);
    
}
