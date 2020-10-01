package com.sg.m4herosightings.dao;

import com.sg.m4herosightings.dto.Hero;
import com.sg.m4herosightings.dto.Location;
import com.sg.m4herosightings.dto.Organization;
import com.sg.m4herosightings.dto.Sighting;
import com.sg.m4herosightings.dto.Superpower;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
<<<<<<< HEAD
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
=======
import static org.junit.jupiter.api.Assertions.*;
>>>>>>> b2c979cafac6e241131e3015f9500cfe04c4ff2b
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HeroDaoDBTest {

    @Autowired
    SuperpowerDao superpowerDao;

    @Autowired
    HeroDao heroDao;
<<<<<<< HEAD
    
    @Autowired
    OrganizationDao organizationDao;
    
    @Autowired
    LocationDao locationDao;
    
    @Autowired
    SightingDao sightingDao;
    
=======

>>>>>>> b2c979cafac6e241131e3015f9500cfe04c4ff2b
    public HeroDaoDBTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
<<<<<<< HEAD
        List<Sighting> sightings = sightingDao.readAllSightings();
        for (Sighting s : sightings) {
            sightingDao.deleteSightingById(s.getSightingId());
        }
        
        List<Organization> orgs = organizationDao.readAllOrganization();
        for (Organization o : orgs) {
            organizationDao.deleteOrganization(o.getOrganizationId());
        }
        
        List<Location> locations = locationDao.readAllLocations();
        for (Location l : locations) {
            locationDao.deleteLocationById(l.getLocationId());
=======
        List<Superpower> superpowers = superpowerDao.readAllSuperpowers();
        for (Superpower superpower : superpowers) {
            superpowerDao.deleteSuperpowerById(superpower.getSuperpowerId());
>>>>>>> b2c979cafac6e241131e3015f9500cfe04c4ff2b
        }

        List<Hero> heroes = heroDao.readAllHeroes();
<<<<<<< HEAD
        for (Hero h : heroes) {
            heroDao.deleteHeroById(h.getHeroId());
        }
        
        List<Superpower> superpowers = superpowerDao.readAllSuperpowers();
        for (Superpower sp : superpowers) {
            superpowerDao.deleteSuperpowerById(sp.getSuperpowerId());
=======
        for (Hero hero : heroes) {
            heroDao.deleteHeroById(hero.getHeroId());
>>>>>>> b2c979cafac6e241131e3015f9500cfe04c4ff2b
        }
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAllSuperpowers method, of class SuperpowerDaoDB.
     */
    @Test
    public void testGetAllHeroes() {
        Superpower superpower1 = new Superpower();
        superpower1.setName("drink");
        superpower1.setDescription("drink a gallon of whisky in a second and still stay sober");
        superpower1 = superpowerDao.createSuperpower(superpower1);

        Hero hero1 = new Hero();
        hero1.setName("UncleAlkash");
        hero1.setDescription("Number one sober-alcoholic");
        hero1.setSuperpower(superpower1);
        hero1 = heroDao.createHero(hero1);

        Superpower superpower2 = new Superpower();
        superpower2.setName("drink");
        superpower2.setDescription("drink a gallon of whisky in a second and still stay sober");
        superpower2 = superpowerDao.createSuperpower(superpower2);

        Hero hero2 = new Hero();
        hero2.setName("UncleAlkash");
        hero2.setDescription("Number one sober-alcoholic");
        hero2.setSuperpower(superpower2);
        hero2 = heroDao.createHero(hero2);

        List<Hero> heroes = heroDao.readAllHeroes();

        assertEquals(2, heroes.size());
        assertTrue(heroes.contains(hero1));
        assertTrue(heroes.contains(hero2));
    }

    /**
     * Test of addSuperpower method, of class SuperpowerDaoDB.
     */
    @Test
    public void testAddGetHero() {
        Superpower superpower = new Superpower();
        superpower.setName("drink");
        superpower.setDescription("drink a gallon of whisky in a second and still stay sober");
        superpower = superpowerDao.createSuperpower(superpower);

        Hero hero = new Hero();
        hero.setName("UncleAlkash");
        hero.setDescription("Number one sober-alcoholic");
        hero.setSuperpower(superpower);
        hero = heroDao.createHero(hero);

        Hero fromDao = heroDao.readHeroById(hero.getHeroId());

        assertEquals(fromDao, hero);
    }

    /**
     * Test of updateSuperpower method, of class SuperpowerDaoDB.
     */
    @Test
    public void testUpdateHero() {
        Superpower superpower = new Superpower();
        superpower.setName("drink");
        superpower.setDescription("drink a gallon of whisky in a second and still stay sober");
        superpower = superpowerDao.createSuperpower(superpower);

        Hero hero = new Hero();
        hero.setName("UncleAlkash");
        hero.setDescription("Number one sober-alcoholic");
        hero.setSuperpower(superpower);
        hero = heroDao.createHero(hero);

        Hero fromDao = heroDao.readHeroById(hero.getHeroId());
        assertEquals(fromDao, hero);

        hero.setName("UncleBob");
        heroDao.updateHero(hero);
        assertNotEquals(fromDao, hero);

        fromDao = heroDao.readHeroById(hero.getHeroId());
        assertEquals(fromDao, hero);

        Superpower superpower1 = new Superpower();
        superpower1.setName("Good eyes");
        superpower1.setDescription("Always can find you");
        superpower1 = superpowerDao.createSuperpower(superpower1);
        hero.setSuperpower(superpower1);
        heroDao.updateHero(hero);

        assertNotEquals(fromDao, hero);

        fromDao = heroDao.readHeroById(hero.getHeroId());

        assertEquals(fromDao, hero);
    }

    /**
     * Test of deleteSuperpowerById method, of class SuperpowerDaoDB.
     */
    @Test
    public void testDeleteHero() {
<<<<<<< HEAD
=======
        //arrange
>>>>>>> b2c979cafac6e241131e3015f9500cfe04c4ff2b
        Superpower superpower = new Superpower();
        superpower.setName("drink");
        superpower.setDescription("drink a gallon of whisky in a second and still stay sober");
        superpower = superpowerDao.createSuperpower(superpower);
<<<<<<< HEAD
        
=======

>>>>>>> b2c979cafac6e241131e3015f9500cfe04c4ff2b
        Hero hero = new Hero();
        hero.setName("UncleAlkash");
        hero.setDescription("Number one sober-alcoholic");
        hero.setSuperpower(superpower);
        hero = heroDao.createHero(hero);
<<<<<<< HEAD
        
        Hero fromDao = heroDao.readHeroById(hero.getHeroId());
        
        assertEquals(fromDao, hero);
        
        boolean deleted = heroDao.deleteHeroById(hero.getHeroId());
        
        assertTrue(deleted);
        
        fromDao = heroDao.readHeroById(hero.getHeroId());
        
        assertNull(fromDao);
=======

        //act
        Hero original = heroDao.readHeroById(hero.getHeroId());

        boolean deleted = heroDao.deleteHeroById(hero.getHeroId());

        Hero afterDel = heroDao.readHeroById(original.getHeroId());

        //assert
        assertNotNull(original);
        assertTrue(deleted);
        assertNull(afterDel);
>>>>>>> b2c979cafac6e241131e3015f9500cfe04c4ff2b
    }

}
