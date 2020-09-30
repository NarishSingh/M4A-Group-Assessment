package com.sg.m4herosightings.dao;

import com.sg.m4herosightings.dto.Hero;
import java.util.List;

public interface HeroDao {
  
    Hero createHero(Hero hero);
    
    Hero readHeroById(int id);
    
    List<Hero> readAllHeroes();
  
    void updateHero(Hero hero);
  
    void deleteHero(int id);
    
}
