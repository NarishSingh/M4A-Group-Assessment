package com.sg.m4herosightings.dao;

import com.sg.m4herosightings.dto.Hero;
import com.sg.m4herosightings.dto.Location;
import com.sg.m4herosightings.dto.Sighting;
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
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)

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
        
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of createSighting method, of class SightingDao.
     */
    @Test
    public void testCreateSighting() {
    }

    /**
     * Test of readSightingById method, of class SightingDao.
     */
    @Test
    public void testReadSightingById() {
    }

    /**
     * Test of updateSighting method, of class SightingDao.
     */
    @Test
    public void testUpdateSighting() {
    }

    /**
     * Test of deleteSightingById method, of class SightingDao.
     */
    @Test
    public void testDeleteSightingById() {
    }

    /**
     * Test of readHeroSightingsByLocation method, of class SightingDao.
     */
    @Test
    public void testReadHeroSightingsByLocation() {
    }

    /**
     * Test of readLocationSightingsByHero method, of class SightingDao.
     */
    @Test
    public void testReadLocationSightingsByHero() {
    }

    /**
     * Test of readSightingsByDate method, of class SightingDao.
     */
    @Test
    public void testReadSightingsByDate() {
    }

}
