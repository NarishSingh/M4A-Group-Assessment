/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.m4herosightings.dao;

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

/**
 *
 * @author irabob
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SuperpowerDaoDBTest {
    
    @Autowired
    SuperpowerDao superpowerDao;
    
    @Autowired
    HeroDao heroDao;
    
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
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        for(Superpower superpower: superpowers){
            superpowerDao.deleteSuperpower(superpower.getSuperpowerId());
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
        superpower1 = superpowerDao.addSuperpower(superpower1);
        
        Superpower superpower2 = new Superpower();
        superpower2.setName("drinkk");
        superpower2.setDescription("drink a two gallon of whisky in a second and still stay sober");
        superpower2 = superpowerDao.addSuperpower(superpower2);
        
        Superpower superpower3 = new Superpower();
        superpower3.setName("drinkkk");
        superpower3.setDescription("drink a three gallon of whisky in a second and still stay sober");
        
        List<Superpower> superpowers = superpowerDao.getAllSuperpowers();
        
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
        superpower = superpowerDao.addSuperpower(superpower);
        
        Superpower fromDao = superpowerDao.getSuperpowerById(superpower.getSuperpowerId());
        
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
        superpower = superpowerDao.addSuperpower(superpower);
        
        Superpower fromDao = superpowerDao.getSuperpowerById(superpower.getSuperpowerId());
        
        assertEquals(superpower, fromDao);
        
        superpower.setName("gallonShot");
        superpowerDao.updateSuperpower(superpower);
        
        assertNotEquals(fromDao, superpower);
        
        fromDao = superpowerDao.getSuperpowerById(superpower.getSuperpowerId());
        
        assertEquals(fromDao, superpower);
        
    }

    /**
     * Test of deleteSuperpower method, of class SuperpowerDaoDB.
     */
    @Test
    public void testDeleteSuperpower() {
    }
    
}
