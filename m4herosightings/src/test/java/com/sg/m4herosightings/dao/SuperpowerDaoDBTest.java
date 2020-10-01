package com.sg.m4herosightings.dao;

import com.sg.m4herosightings.dto.Hero;
import com.sg.m4herosightings.dto.Location;
import com.sg.m4herosightings.dto.Organization;
import com.sg.m4herosightings.dto.Sighting;
import com.sg.m4herosightings.dto.Superpower;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SuperpowerDaoDBTest {
    
    @Autowired
    SuperpowerDao superpowerDao;
    
    @Autowired
    HeroDao heroDao;
    
    @Autowired
    OrganizationDao organizationDao;
    
    @Autowired
    LocationDao locationDao;
    
    @Autowired
    SightingDao sightingDao;
    
    public SuperpowerDaoDBTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
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
        }
        
        List<Hero> heroes = heroDao.readAllHeroes();
        for (Hero h : heroes) {
            heroDao.deleteHero(h.getHeroId());
        }
        
        List<Superpower> superpowers = superpowerDao.readAllSuperpowers();
        for (Superpower sp : superpowers) {
            superpowerDao.deleteSuperpowerById(sp.getSuperpowerId());
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAllSuperpowers method, of class SuperpowerDaoDB.
     */
    @Test
    public void testGetAllSuperpowers() {
        Superpower superpower1 = new Superpower();
        superpower1.setName("drink");
        superpower1.setDescription("drink a gallon of whisky in a second and still stay sober");
        superpower1 = superpowerDao.createSuperpower(superpower1);
        
        Superpower superpower2 = new Superpower();
        superpower2.setName("drinkk");
        superpower2.setDescription("drink a two gallon of whisky in a second and still stay sober");
        superpower2 = superpowerDao.createSuperpower(superpower2);
        
        Superpower superpower3 = new Superpower();
        superpower3.setName("drinkkk");
        superpower3.setDescription("drink a three gallon of whisky in a second and still stay sober");
        
        List<Superpower> superpowers = superpowerDao.readAllSuperpowers();
        
        assertEquals(2, superpowers.size());
        assertTrue(superpowers.contains(superpower1));
        assertTrue(superpowers.contains(superpower2));
        assertFalse(superpowers.contains(superpower3));
    }

    /**
     * Test of addSuperpower method, of class SuperpowerDaoDB.
     */
    @Test
    public void testAddGetSuperpower() {
        Superpower superpower = new Superpower();
        superpower.setName("drink");
        superpower.setDescription("drink a gallon of whisky in a second and still stay sober");
        superpower = superpowerDao.createSuperpower(superpower);
        
        Superpower fromDao = superpowerDao.readSuperpowerById(superpower.getSuperpowerId());
        
        assertEquals(superpower, fromDao);
    }

    /**
     * Test of updateSuperpower method, of class SuperpowerDaoDB.
     */
    @Test
    public void testUpdateSuperpower() {
        Superpower superpower = new Superpower();
        superpower.setName("drink");
        superpower.setDescription("drink a gallon of whisky in a second and still stay sober");
        superpower = superpowerDao.createSuperpower(superpower);
        
        Superpower fromDao = superpowerDao.readSuperpowerById(superpower.getSuperpowerId());
        
        assertEquals(superpower, fromDao);
        
        superpower.setName("gallonShot");
        superpowerDao.updateSuperpower(superpower);
        
        assertNotEquals(fromDao, superpower);
        
        fromDao = superpowerDao.readSuperpowerById(superpower.getSuperpowerId());
        
        assertEquals(fromDao, superpower);
        
    }

    /**
     * Test of deleteSuperpower method, of class SuperpowerDaoDB.
     */
    @Test
    public void testDeleteSuperpower() {
    }
    
}
