package com.sg.m4herosightings.dao;

import com.sg.m4herosightings.dto.Hero;
import com.sg.m4herosightings.dto.Location;
import com.sg.m4herosightings.dto.Organization;
import com.sg.m4herosightings.dto.Sighting;
import com.sg.m4herosightings.dto.Superpower;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrganizationDaoDbTest {

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

    public OrganizationDaoDbTest() {
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
        List<Organization> orgs = organizationDao.readAllOrganizations();
        for (Organization o : orgs) {
            organizationDao.deleteOrganizationById(o.getOrganizationId());
        }
        List<Location> locations = locationDao.readAllLocations();
        for (Location l : locations) {
            locationDao.deleteLocationById(l.getLocationId());
        }
        List<Hero> heroes = heroDao.readAllHeroes();
        for (Hero h : heroes) {
            heroDao.deleteHeroById(h.getHeroId());
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
    public void testGetAllOrganization() {
        Superpower superpower = new Superpower();
        superpower.setName("drink");
        superpower.setDescription("drink a gallon of whisky in a second and still stay sober");
        superpower = superpowerDao.createSuperpower(superpower);
        Hero hero = new Hero();
        hero.setName("UncleAlkash");
        hero.setDescription("Number one sober-alcoholic");
        hero.setSuperpower(superpower);
        hero = heroDao.createHero(hero);
        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero);
        Location location = new Location();
        location.setCity("Staten Island");
        location.setStreet("hyland bldv");
        location.setState("NY");
        location.setZipcode("10303");
        location.setDescription("By the ferry");
        location.setLongitude(40.61191);
        location.setLatitude(-74.06392);
        location.setName("Rose Bank Pizza");
        location = locationDao.createLocation(location);
        Organization org = new Organization();
        org.setName("Anonymous Organization");
        org.setDescription("No one knows");
        org.setPhone("xxx-xxx-xxxx");
        org.setEmail("idk@leaveme.com");
        org.setMembers(heroes);
        org.setLocation(location);
        org = organizationDao.createOrganization(org);
        Superpower superpower1 = new Superpower();
        superpower1.setName("drinkkk");
        superpower1.setDescription("drink a gallon of whisky in a second and still stay soberrr");
        superpower1 = superpowerDao.createSuperpower(superpower1);
        Hero hero1 = new Hero();
        hero1.setName("Uncle-Alkash");
        hero1.setDescription("Number one sober-alcoholic");
        hero1.setSuperpower(superpower1);
        hero1 = heroDao.createHero(hero1);
        List<Hero> heroes1 = new ArrayList<>();
        heroes1.add(hero1);
        Location location1 = new Location();
        location1.setCity("Staten Island");
        location1.setStreet("hyland bldv");
        location1.setState("NY");
        location1.setZipcode("10303");
        location1.setDescription("By the ferry");
        location1.setLongitude(40.61191);
        location1.setLatitude(-74.06392);
        location1.setName("Rose Bank Pizza");
        location1 = locationDao.createLocation(location1);
        Organization org1 = new Organization();
        org1.setName("Anonymous Organization");
        org1.setDescription("No one knows");
        org1.setPhone("xxx-xxx-xxxx");
        org1.setEmail("idk@leaveme.com");
        org1.setMembers(heroes1);
        org1.setLocation(location1);
        org1 = organizationDao.createOrganization(org1);
        List<Organization> organizations = organizationDao.readAllOrganizations();
        assertEquals(organizations.size(), 2);
        assertTrue(organizations.contains(org));
        assertTrue(organizations.contains(org1));
    }

    /**
     * Test of addSuperpower method, of class SuperpowerDaoDB.
     */
    @Test
    public void testAddGetOrganization() {
        Superpower superpower = new Superpower();
        superpower.setName("drink");
        superpower.setDescription("drink a gallon of whisky in a second and still stay sober");
        superpower = superpowerDao.createSuperpower(superpower);
        Hero hero = new Hero();
        hero.setName("UncleAlkash");
        hero.setDescription("Number one sober-alcoholic");
        hero.setSuperpower(superpower);
        hero = heroDao.createHero(hero);
        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero);
        Location location = new Location();
        location.setCity("Staten Island");
        location.setStreet("hyland bldv");
        location.setState("NY");
        location.setZipcode("10303");
        location.setDescription("By the ferry");
        location.setLongitude(40.61191);
        location.setLatitude(-74.06392);
        location.setName("Rose Bank Pizza");
        location = locationDao.createLocation(location);
        Organization org = new Organization();
        org.setName("Anonymous Organization");
        org.setDescription("No one knows");
        org.setPhone("xxx-xxx-xxxx");
        org.setEmail("idk@leaveme.com");
        org.setMembers(heroes);
        org.setLocation(location);
        org = organizationDao.createOrganization(org);
        Organization fromDao = organizationDao.readOrganizationById(org.getOrganizationId());
        assertEquals(fromDao, org);
        org.setName("Hero Bazaar");
        organizationDao.updateOrganization(org);
        assertNotEquals(fromDao, org);
        fromDao = organizationDao.readOrganizationById(org.getOrganizationId());
        assertEquals(fromDao, org);
        Location location1 = new Location();
        location1.setCity("Brooklyn");
        location1.setStreet("Neptune rd");
        location1.setState("NY");
        location1.setZipcode("11010");
        location1.setDescription("By Coney Island");
        location1.setLongitude(40.61191);
        location1.setLatitude(-74.06392);
        location1.setName("Bank of America");
        location1 = locationDao.createLocation(location1);
        org.setLocation(location1);
        organizationDao.updateOrganization(org);
        assertNotEquals(fromDao, org);
    }

    /**
     * Test of updateSuperpower method, of class SuperpowerDaoDB.
     */
    @Test
    public void testUpdateOrganization() {
        Superpower superpower = new Superpower();
        superpower.setName("drink");
        superpower.setDescription("drink a gallon of whisky in a second and still stay sober");
        superpower = superpowerDao.createSuperpower(superpower);
        Hero hero = new Hero();
        hero.setName("UncleAlkash");
        hero.setDescription("Number one sober-alcoholic");
        hero.setSuperpower(superpower);
        hero = heroDao.createHero(hero);
        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero);
        Location location = new Location();
        location.setCity("Staten Island");
        location.setStreet("hyland bldv");
        location.setState("NY");
        location.setZipcode("10303");
        location.setDescription("By the ferry");
        location.setLongitude(40.61191);
        location.setLatitude(-74.06392);
        location.setName("Rose Bank Pizza");
        location = locationDao.createLocation(location);
        Organization org = new Organization();
        org.setName("Anonymous Organization");
        org.setDescription("No one knows");
        org.setPhone("xxx-xxx-xxxx");
        org.setEmail("idk@leaveme.com");
        org.setMembers(heroes);
        org.setLocation(location);
        org = organizationDao.createOrganization(org);
        Organization fromDao = organizationDao.readOrganizationById(org.getOrganizationId());
        assertEquals(fromDao, org);
    }

    /**
     * Test of deleteSuperpower method, of class SuperpowerDaoDB.
     */
    @Test
    public void testDeleteOrganization() {
        Superpower superpower = new Superpower();
        superpower.setName("drink");
        superpower.setDescription("drink a gallon of whisky in a second and still stay sober");
        superpower = superpowerDao.createSuperpower(superpower);
        Hero hero = new Hero();
        hero.setName("UncleAlkash");
        hero.setDescription("Number one sober-alcoholic");
        hero.setSuperpower(superpower);
        hero = heroDao.createHero(hero);
        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero);
        Location location = new Location();
        location.setCity("Staten Island");
        location.setStreet("hyland bldv");
        location.setState("NY");
        location.setZipcode("10303");
        location.setDescription("By the ferry");
        location.setLongitude(40.61191);
        location.setLatitude(-74.06392);
        location.setName("Rose Bank Pizza");
        location = locationDao.createLocation(location);
        Organization org = new Organization();
        org.setName("Anonymous Organization");
        org.setDescription("No one knows");
        org.setPhone("xxx-xxx-xxxx");
        org.setEmail("idk@leaveme.com");
        org.setMembers(heroes);
        org.setLocation(location);
        org = organizationDao.createOrganization(org);
        Organization fromDao = organizationDao.readOrganizationById(org.getOrganizationId());
        assertEquals(fromDao, org);
        boolean deleted = organizationDao.deleteOrganizationById(org.getOrganizationId());
        assertTrue(deleted);
        fromDao = organizationDao.readOrganizationById(org.getOrganizationId());
        assertNull(fromDao);
    }

    /**
     * Test of readHeroesForOrganization method. of class OrganizationDaoDb
     */
    @Test
    public void testReadHeroesForOrganization() {
        Superpower superpower = new Superpower();
        superpower.setName("drink");
        superpower.setDescription("drink a gallon of whisky in a second and still stay sober");
        superpower = superpowerDao.createSuperpower(superpower);
        Hero hero = new Hero();
        hero.setName("UncleAlkash");
        hero.setDescription("Number one sober-alcoholic");
        hero.setSuperpower(superpower);
        hero = heroDao.createHero(hero);
        Superpower superpower1 = new Superpower();
        superpower1.setName("drinkkk");
        superpower1.setDescription("drink a gallon of whisky in a second and still stay soberrr");
        superpower1 = superpowerDao.createSuperpower(superpower1);
        Hero hero1 = new Hero();
        hero1.setName("Test 2");
        hero1.setDescription("Test Description");
        hero1.setSuperpower(superpower1);
        hero1 = heroDao.createHero(hero1);
        Superpower superpower2 = new Superpower();
        superpower2.setName("test2");
        superpower2.setDescription("test drink a gallon of whisky in a second and still stay soberrr");
        superpower2 = superpowerDao.createSuperpower(superpower2);
        Hero hero2 = new Hero();
        hero2.setName("Test 3");
        hero2.setDescription("Test 3 Description");
        hero2.setSuperpower(superpower2);
        hero2 = heroDao.createHero(hero2);
        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero);
        heroes.add(hero1);
        Location location = new Location();
        location.setCity("Staten Island");
        location.setStreet("hyland bldv");
        location.setState("NY");
        location.setZipcode("10303");
        location.setDescription("By the ferry");
        location.setLongitude(40.61191);
        location.setLatitude(-74.06392);
        location.setName("Rose Bank Pizza");
        location = locationDao.createLocation(location);
        Organization org = new Organization();
        org.setName("Anonymous Organization");
        org.setDescription("No one knows");
        org.setPhone("xxx-xxx-xxxx");
        org.setEmail("idk@leaveme.com");
        org.setMembers(heroes);
        org.setLocation(location);
        org = organizationDao.createOrganization(org);
        List<Hero> testHeroes = organizationDao.readHeroesForOrganization(org);
        assertEquals(2, testHeroes.size());
        assertFalse(testHeroes.contains(hero2));
    }

    /**
     * Test of displayOrganizationForHero method. of class OrganizationDaoDb
     */
    @Test
    public void testDisplayOrganizationForHero() {
        Superpower superpower = new Superpower();
        superpower.setName("drink");
        superpower.setDescription("drink a gallon of whisky in a second and still stay sober");
        superpower = superpowerDao.createSuperpower(superpower);
        Hero hero = new Hero();
        hero.setName("UncleAlkash");
        hero.setDescription("Number one sober-alcoholic");
        hero.setSuperpower(superpower);
        hero = heroDao.createHero(hero);
        List<Hero> heroes = new ArrayList<>();
        heroes.add(hero);
        Location location = new Location();
        location.setCity("Staten Island");
        location.setStreet("hyland bldv");
        location.setState("NY");
        location.setZipcode("10303");
        location.setDescription("By the ferry");
        location.setLongitude(40.61191);
        location.setLatitude(-74.06392);
        location.setName("Rose Bank Pizza");
        location = locationDao.createLocation(location);
        Organization org = new Organization();
        org.setName("Anonymous Organization");
        org.setDescription("No one knows");
        org.setPhone("xxx-xxx-xxxx");
        org.setEmail("idk@leaveme.com");
        org.setMembers(heroes);
        org.setLocation(location);
        organizationDao.createOrganization(org);
        Location location1 = new Location();
        location1.setCity("Staten Island");
        location1.setStreet("hyland bldv");
        location1.setState("NY");
        location1.setZipcode("10303");
        location1.setDescription("By the ferry");
        location1.setLongitude(40.61191);
        location1.setLatitude(-74.06392);
        location1.setName("Rose Bank Pizza");
        location1 = locationDao.createLocation(location1);
        Organization org1 = new Organization();
        org1.setName("Anonymous Organization");
        org1.setDescription("No one knows");
        org1.setPhone("xxx-xxx-xxxx");
        org1.setEmail("idk@leaveme.com");
        org1.setMembers(heroes);
        org1.setLocation(location1);
        organizationDao.createOrganization(org1);
        List<Organization> orgs = organizationDao.displayOrganizationForHero(hero);
        assertEquals(2, orgs.size());
        assertTrue(orgs.contains(org));
        assertTrue(orgs.contains(org1));
    }

}
