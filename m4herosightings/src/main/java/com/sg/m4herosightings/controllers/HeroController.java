package com.sg.m4herosightings.controllers;

import com.sg.m4herosightings.dao.HeroDao;
import com.sg.m4herosightings.dao.LocationDao;
import com.sg.m4herosightings.dao.OrganizationDao;
import com.sg.m4herosightings.dao.SightingDao;
import com.sg.m4herosightings.dao.SuperpowerDao;
import com.sg.m4herosightings.dto.Hero;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HeroController {
    
    @Autowired
    SuperpowerDao spDao;
    
    @Autowired
    HeroDao hDao;
    
    @Autowired
    OrganizationDao oDao;
    
    @Autowired
    LocationDao ldao;
    
    @Autowired
    SightingDao siDao;
    
    Set<ConstraintViolation<Hero>> violations = new HashSet<>();
    
    /**
     * GET - on loading subdomain, load all heroes from db to be rendered to table
     * @param model {Model} will hold all heroes from db
     * @return {String} same subdomain
     */
    @GetMapping("heroes")
    public String displayHeroes(Model model){
        List<Hero> heroes = hDao.readAllHeroes();
        model.addAttribute("heroes", heroes);
        
        return "heroes";
    }
    
    /*DETAILS*/
//    @GetMapping("viewHero")
    
    
    /*EDIT*/
//    @GetMapping("editHero")
    
    /*DELETE*/
//    @GetMapping("deleteHero")
    
    
}
