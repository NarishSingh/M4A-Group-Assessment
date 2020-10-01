package com.sg.m4herosightings.dao;

import com.sg.m4herosightings.dto.Hero;
import com.sg.m4herosightings.dto.Location;
import com.sg.m4herosightings.dto.Organization;
import com.sg.m4herosightings.dto.Sighting;
import com.sg.m4herosightings.dto.Superpower;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocationDaoTest {
    
    @Autowired
    HeroDao hDao;

    @Autowired
    SuperpowerDao spDao;

    @Autowired
    LocationDao locDao;

    @Autowired
    OrganizationDao orgDao;

    @Autowired
    SightingDao sightDao;
    
    static Location l1;
    static Location l2;
    static Location l3;
    
    public LocationDaoTest() {
    }
    

    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        /*clean db*/
        List<Superpower> superpowers = spDao.readAllSuperpowers();
        for (Superpower sp : superpowers) {
            spDao.deleteSuperpowerById(sp.getSuperpowerId());
        }

        List<Hero> heroes = hDao.readAllHeroes();
        for (Hero h : heroes) {
            hDao.deleteHeroById(h.getHeroId());
        }

        List<Location> locations = locDao.readAllLocations();
        for (Location l : locations) {
            locDao.deleteLocationById(l.getLocationId());
        }

        List<Organization> orgs = orgDao.readAllOrganizations();
        for (Organization o : orgs) {
            orgDao.deleteOrganizationById(o.getOrganizationId());
        }

        List<Sighting> sightings = sightDao.readAllSightings();
        for (Sighting s : sightings) {
            sightDao.deleteSightingById(s.getSightingId());
        }
        
        /*locations*/
        l1 = new Location();
        l1.setName("test Empire State Building");
        l1.setDescription("testing");
        l1.setStreet("20 W 34th St");
        l1.setCity("New York");
        l1.setState("NY");
        l1.setZipcode("10001");
        l1.setLatitude(40.748817);
        l1.setLongitude(-73.985428);

        l2 = new Location();
        l2.setName("test Grand Central Terminal");
        l2.setDescription("testing2");
        l2.setStreet("89 E 42nd St");
        l2.setCity("New York");
        l2.setState("NY");
        l2.setZipcode("10017");
        l2.setLatitude(40.752655);
        l2.setLongitude(-73.977295);
        
        l3 = new Location();
        l3.setName("test Lincoln Memorial");
        l3.setDescription("testing3");
        l3.setStreet("2 Lincoln Memorial Cir NW");
        l3.setCity("Washington");
        l3.setState("DC");
        l3.setZipcode("20002");
        l3.setLatitude(38.889248);
        l3.setLongitude(-77.050636);
    }
    

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of createLocation method, of class LocationDao.
     */
    @Test
    public void testCreateReadLocation() {
        //arrange
        Location locCreated = locDao.createLocation(l1);
        
        //act
        Location fromDao = locDao.readLocationById(locCreated.getLocationId());
        
        //assert
        assertNotNull(locCreated);
        assertNotNull(fromDao);
        assertEquals(locCreated, fromDao);
    }

    /**
     * Test of readAllLocations method, of class LocationDao.
     */
    @Test
    public void testReadAllLocations() {
        //arrange
        Location loc1 = locDao.createLocation(l1);
        Location loc2 = locDao.createLocation(l2);
        
        //act
        List<Location> allLocations = locDao.readAllLocations();
        
        //assert
        assertEquals(allLocations.size(), 2);
        assertTrue(allLocations.contains(loc1));
        assertTrue(allLocations.contains(loc1));
        assertFalse(allLocations.contains(l3));
    }

    /**
     * Test of updateLocation method, of class LocationDao.
     */
    @Test
    public void testUpdateLocation() {
        //arrange
        Location loc1 = locDao.createLocation(l1);
        
        //act
        Location original = locDao.readLocationById(loc1.getLocationId());
        
        loc1.setName("Edit Name");
        loc1.setDescription("Edit description");
        
        Location loc1u = locDao.updateLocation(loc1);
        Location edit = locDao.readLocationById(loc1.getLocationId());
        
        //assert
        assertNotNull(original);
        assertNotNull(edit);
        assertNotEquals(original, edit);
    }

    /**
     * Test of deleteLocationById method, of class LocationDao.
     */
    @Test
    public void testDeleteLocationById() {
        //arrange
        Location loc1 = locDao.createLocation(l1);
        
        //act
        boolean deleted = locDao.deleteLocationById(loc1.getLocationId());
        Location afterDel = locDao.readLocationById(loc1.getLocationId());
        
        //assert
        assertTrue(deleted);
        assertNull(afterDel);
    }
    
}
