package com.sg.m4herosightings.dao;

import com.sg.m4herosightings.dto.Hero;
import com.sg.m4herosightings.dto.Location;
import com.sg.m4herosightings.dto.Organization;
import com.sg.m4herosightings.dto.Sighting;
import com.sg.m4herosightings.dto.Superpower;
import java.time.LocalDate;
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
public class SightingDaoTest {

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

    static Superpower sp1;

    static Hero h1;
    static Hero h2;
    static Hero h3;

    static Location l1;
    static Location l2;
    static Location l3;

    static Sighting s1;
    static Sighting s2;
    static Sighting s3;
    static Sighting s4;

    public SightingDaoTest() {
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

        /*superpowers*/
        sp1.setName("Fly");
        sp1.setDescription("Can fly");

        /*hero/villians*/
        h1.setName("Hero1");
        h1.setDescription("test");
        h1.setSuperpower(sp1);

        h2.setName("Hero2");
        h2.setDescription("test2");
        h2.setSuperpower(sp1);

        h3.setName("Hero3");
        h3.setDescription("test3");
        h3.setSuperpower(sp1);

        /*locations*/
        l1.setName("test Empire State Building");
        l1.setDescription("testing");
        l1.setStreet("20 W 34th St");
        l1.setCity("New York");
        l1.setState("NY");
        l1.setZipcode("10001");
        l1.setLatitude(40.748817);
        l1.setLongitude(-73.985428);

        l2.setName("test Grand Central Terminal");
        l2.setDescription("testing2");
        l2.setStreet("89 E 42nd St");
        l2.setCity("New York");
        l2.setState("NY");
        l2.setZipcode("10017");
        l2.setLatitude(40.752655);
        l2.setLongitude(-73.977295);

        /*sightings*/
        s1.setDate(LocalDate.now());
        s1.setDescription("Encounter");
        s1.setHero(h1);
        s1.setLocation(l1);

        //same day and location
        s2.setDate(LocalDate.now());
        s2.setDescription("Encounter2");
        s2.setHero(h2);
        s2.setLocation(l1);

        //same day, hero 1
        s3.setDate(LocalDate.now());
        s3.setDescription("Encounter3");
        s3.setHero(h1);
        s3.setLocation(l2);

        //all different
        s4.setDate(LocalDate.now().minusWeeks(1));
        s4.setDescription("Encounter3");
        s4.setHero(h3);
        s4.setLocation(l2);

    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of createSighting method, of class SightingDao.
     */
    @Test
    public void testCreateReadSighting() {
        //arrange
        spDao.createSuperpower(sp1);
        hDao.createHero(h1);
        locDao.createLocation(l1);

        //act
        sightDao.createSighting(s1);

        Sighting fromDao = sightDao.readSightingById(s1.getSightingId());

        //assert
        assertNotNull(fromDao);
    }

    /**
     * Test of updateSighting method, of class SightingDao.
     */
    @Test
    public void testUpdateSighting() {
        //arrange
        spDao.createSuperpower(sp1);
        hDao.createHero(h1);
        hDao.createHero(h2);
        locDao.createLocation(l1);

        //act
        sightDao.createSighting(s1);
        Sighting original = sightDao.readSightingById(s1.getSightingId());

        s1.setHero(h2);
        sightDao.updateSighting(s1);
        Sighting updated = sightDao.readSightingById(s1.getSightingId());

        //assert
        assertNotNull(s1);
        assertNotEquals(original, updated);
    }

    /**
     * Test of deleteSightingById method, of class SightingDao.
     */
    @Test
    public void testDeleteSightingById() {
        //arrange
        spDao.createSuperpower(sp1);
        hDao.createHero(h1);
        locDao.createLocation(l1);

        //act
        sightDao.createSighting(s1);
        Sighting original = sightDao.readSightingById(s1.getSightingId());

        sightDao.deleteSightingById(s1.getSightingId());
        Sighting deleted = sightDao.readSightingById(original.getSightingId());

        //assert
        assertNotNull(original);
        assertNull(deleted);
    }

    /**
     * Test of readAllSightings method, of class SightingDao.
     */
    @Test
    public void testReadAllSightings() {
        //arrange
        spDao.createSuperpower(sp1);
        hDao.createHero(h1);
        hDao.createHero(h2);
        locDao.createLocation(l1);

        //act
        sightDao.createSighting(s1);
        sightDao.createSighting(s2);

        List<Sighting> allSightings = sightDao.readAllSightings();

        //assert
        assertTrue(allSightings.contains(s1));
        assertTrue(allSightings.contains(s2));
        assertEquals(allSightings.size(), 2);
    }

    /**
     * Test of readHeroSightingsByLocation method, of class SightingDao.
     */
    @Test
    public void testReadHeroSightingsByLocation() {
        //arrange
        spDao.createSuperpower(sp1);
        hDao.createHero(h1);
        hDao.createHero(h2);
        hDao.createHero(h3);
        locDao.createLocation(l1);

        //act
        sightDao.createSighting(s1);
        sightDao.createSighting(s2);

        List<Hero> heroesEmpireState = sightDao.readHeroSightingsByLocation(l1);
        List<Hero> allHeroes = hDao.readAllHeroes();

        //assert
        assertTrue(heroesEmpireState.contains(h1));
        assertTrue(heroesEmpireState.contains(h2));
        assertEquals(heroesEmpireState.size(), 2);
        assertEquals(heroesEmpireState, allHeroes);
    }

    /**
     * Test of readLocationSightingsByHero method, of class SightingDao.
     */
    @Test
    public void testReadLocationSightingsByHero() {
        //arrange
        spDao.createSuperpower(sp1);
        hDao.createHero(h1);
        locDao.createLocation(l1);
        locDao.createLocation(l2);

        //act
        sightDao.createSighting(s1);
        sightDao.createSighting(s3);

        List<Location> h1Locations = sightDao.readLocationSightingsByHero(h1);

        //assert
        assertEquals(h1Locations.size(), 2);
        assertTrue(h1Locations.contains(l1));
        assertTrue(h1Locations.contains(l2));
    }

    /**
     * Test of readSightingsByDate method, of class SightingDao.
     */
    @Test
    public void testReadSightingsByDate() {
        //arrange
        spDao.createSuperpower(sp1);
        hDao.createHero(h1);
        hDao.createHero(h2);
        hDao.createHero(h2);
        locDao.createLocation(l1);
        locDao.createLocation(l2);

        //act
        sightDao.createSighting(s1);
        sightDao.createSighting(s2);
        sightDao.createSighting(s3);
        sightDao.createSighting(s4);

        List<Sighting> sightingToday = sightDao.readSightingsByDate(s1.getDate());

        //assert
        assertEquals(sightingToday.size(), 3);
        assertTrue(sightingToday.contains(s1));
        assertTrue(sightingToday.contains(s2));
        assertTrue(sightingToday.contains(s3));
        assertFalse(sightingToday.contains(s4));
    }

}
