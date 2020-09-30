/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.m4herosightings.dao;

import com.sg.m4herosightings.dto.Hero;
import java.util.List;

/**
 *
 * @author irabob
 */
public interface HeroDao {
    List<Hero> getAllHeroes();
    Hero addHero(Hero hero);
    Hero getHeroById(int id);
    void updateHero(Hero hero);
    void deleteHero(int id);
}
