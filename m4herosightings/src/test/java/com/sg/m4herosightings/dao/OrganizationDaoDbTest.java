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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
//        Superpower superpower1 = new Superpower();
//        superpower1.setName("drink");
//        superpower1.setDescription("drink a gallon of whisky in a second and still stay sober");
//        superpower1 = superpowerDao.createSuperpower(superpower1);
//        
//        Hero hero1 = new Hero();
//        hero1.setName("UncleAlkash");
//        hero1.setDescription("Number one sober-alcoholic");
//        hero1.setSuperpower(superpower1);
//        hero1 = heroDao.createHero(hero1);
//        
//        Superpower superpower2 = new Superpower();
//        superpower2.setName("drink");
//        superpower2.setDescription("drink a gallon of whisky in a second and still stay sober");
//        superpower2 = superpowerDao.createSuperpower(superpower2);
//        
//        Hero hero2 = new Hero();
//        hero2.setName("UncleAlkash");
//        hero2.setDescription("Number one sober-alcoholic");
//        hero2.setSuperpower(superpower2);
//        hero2 = heroDao.createHero(hero2);
//        
//        List<Hero> heroes = heroDao.readAllHeroes();
//        
//        assertEquals(2, heroes.size());
//        assertTrue(heroes.contains(hero1));
//        assertTrue(heroes.contains(hero2));
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
        
        boolean deleted = organizationDao.deleteOrganization(org.getOrganizationId());
        assertTrue(deleted);
        
        fromDao = organizationDao.readOrganizationById(org.getOrganizationId());
        
        assertNull(fromDao);
    }
    
    /**
     * Test of readHeroesForOrganization method. of class OrganizationDaoDb
     */
    @Test 
    public void testReadHeroesForOrganization() {
        
    }
    
    /** 
     * Test of displayOrganizationForHero method. of class OrganizationDaoDb
     */
    @Test
    public void testDisplayOrganizationForHero(){
        
    }
    
}

//Organization{organizationId=2, name=Anonymous Organization, description=No one knows, phone=xxx-xxx-xxxx, email=idk@leaveme.com, location=Location{locationId=2, latitude=-74.06392, longitude=40.61191, name=Rose Bank Pizza, description=By the ferry, street=hyland bldv, city=Staten Island, state=NY, zipcode=10303}, members=[Hero{heroId=35, name=UncleAlkash, description=Number one sober-alcoholic, superpower=null}]}>
//Organization{organizationId=2, name=Anonymous Organization, description=No one knows, phone=xxx-xxx-xxxx, email=idk@leaveme.com, location=Location{locationId=2, latitude=-74.06392, longitude=40.61191, name=Rose Bank Pizza, description=By the ferry, street=hyland bldv, city=Staten Island, state=NY, zipcode=10303}, members=[Hero{heroId=35, name=UncleAlkash, description=Number one sober-alcoholic, superpower=Superpower{superpowerId=46, name=drink, description=drink a gallon of whisky in a second and still stay sober}}]}>